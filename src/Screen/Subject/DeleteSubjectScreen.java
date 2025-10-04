package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│                XÓA MÔN HỌC               │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        try {
            List<String> lines = FileUtil.readLines(FILE_PATH);
            List<Subject> subjects = new ArrayList<>(
                    lines.stream()
                            .map(Subject::fromString)
                            .toList()
            );

            if (subjects.isEmpty()) {
                System.out.println("Hiện chưa có môn học nào trong hệ thống!");
                pause();
                return;
            }

            String id = InputUtil.getNonEmptyString("Nhập mã môn học cần xóa: ");
            Subject s = subjects.stream()
                    .filter(x -> x.getSubjectID().equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(null);

            if (s == null) {
                System.out.println("Không tìm môn học với mã này!");
            } else {
                subjects.remove(s);
                // Save file
                List<String> newLines = subjects.stream().map(Subject::toString).collect(Collectors.toList());
                FileUtil.writeLines(FILE_PATH, newLines);
                System.out.println("Đã xóa môn học thành công!");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc/ghi file: " + e.getMessage());
        }

        pause();
    }
}
