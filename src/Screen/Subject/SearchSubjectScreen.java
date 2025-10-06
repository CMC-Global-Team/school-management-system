package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.List;

public class SearchSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           TÌM KIẾM MÔN HỌC               │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String keyword = InputUtil.getNonEmptyString("Nhập tên hoặc mã môn học: ").toLowerCase();

        try {
            List<String> lines = FileUtil.readLines(FILE_PATH);
            boolean found = false;

            for (String line : lines) {
                Subject s = Subject.fromString(line);
                if (s != null && (s.getSubjectID().equalsIgnoreCase(keyword)
                        || s.getSubjectName().toLowerCase().contains(keyword))) {
                    System.out.println("→ " + s);
                    found = true;
                }
            }

            if (!found) System.out.println("Không tìm thấy môn học phù hợp.");

        } catch (IOException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
