package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;
import java.io.IOException;
import java.util.List;

public class ListSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│          DANH SÁCH MÔN HỌC               │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        try {
            if (!FileUtil.fileExists(FILE_PATH)) {
                System.out.println("Chưa có dữ liệu môn học!");
                return;
            }

            List<String> lines = FileUtil.readLines(FILE_PATH);
            if (lines.isEmpty()) {
                System.out.println("Danh sách trống!");
                return;
            }

            System.out.printf("%-10s | %-20s | %-6s | %-5s | %-12s | %-15s | %-15s | %-10s%n",
                    "Mã", "Tên môn", "Tiết", "Hệ số", "Loại môn", "Giáo viên", "Mô tả", "Trạng thái");
            System.out.println("─".repeat(110));

            for (String line : lines) {
                Subject s = Subject.fromString(line);
                if (s != null) {
                    System.out.printf("%-10s | %-20s | %-6d | %-5.1f | %-12s | %-15s | %-15s | %-10s%n",
                            s.getSubjectID(), s.getSubjectName(), s.getLessonCount(), s.getConfficient(),
                            s.getSubjectType(), s.getTeacherInCharge(), s.getDescription(), s.getStatus());
                }
            }

        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
