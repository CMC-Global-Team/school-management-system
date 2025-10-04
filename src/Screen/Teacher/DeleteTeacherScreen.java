package Screen.Teacher;

import Models.Teacher;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteTeacherScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/teachers.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           XÓA GIÁO VIÊN                  │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        try {
            List<String> lines = FileUtil.readLines(FILE_PATH);
            List<Teacher> teachers = lines.stream()
                    .map(Teacher::fromString)
                    .collect(Collectors.toList());

            if (teachers.isEmpty()) {
                System.out.println("Hiện chưa có giáo viên nào trong hệ thống!");
                pause();
                return;
            }

            String id = InputUtil.getNonEmptyString("Nhập mã giáo viên cần xóa: ");
            Teacher t = teachers.stream()
                    .filter(x -> x.getId().equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(null);

            if (t == null) {
                System.out.println("Không tìm thấy giáo viên với mã này!");
            } else {
                teachers.remove(t);
                // Lưu lại file
                List<String> newLines = teachers.stream().map(Teacher::toString).collect(Collectors.toList());
                FileUtil.writeLines(FILE_PATH, newLines);
                System.out.println("Đã xóa giáo viên thành công!");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc/ghi file: " + e.getMessage());
        }

        pause();
    }
}
