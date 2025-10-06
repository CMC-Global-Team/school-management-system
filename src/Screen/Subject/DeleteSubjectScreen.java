package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│             XÓA MÔN HỌC                  │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String id = InputUtil.getNonEmptyString("Nhập mã môn học cần xóa: ");

        try {
            List<String> lines = FileUtil.readLines(FILE_PATH);
            List<String> remaining = new ArrayList<>();
            boolean found = false;

            for (String line : lines) {
                Subject s = Subject.fromString(line);
                if (s != null && s.getSubjectID().equalsIgnoreCase(id)) {
                    found = true;
                    System.out.println("Xóa môn: " + s.getSubjectName());
                } else {
                    remaining.add(line);
                }
            }

            if (found) {
                FileUtil.writeLines(FILE_PATH, remaining);
                System.out.println("Đã xóa thành công!");
            } else {
                System.out.println("Không tìm thấy môn học!");
            }

        } catch (IOException e) {
            System.out.println("Lỗi khi xóa: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
