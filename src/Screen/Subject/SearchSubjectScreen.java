package Screen.Subject;
import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│             TÌM KIẾM MÔN HỌC             │");
        System.out.println("└──────────────────────────────────────────┘");
    }
    @Override
    public void handleInput() {
        try {
            List<String> lines = FileUtil.readLines(FILE_PATH);
            List<Subject> subjects = lines.stream()
                    .map(Subject::fromString)
                    .toList();

            if (subjects.isEmpty()) {
                System.out.println("Hiện chưa có môn học nào trong hệ thống!");
                pause();
                return;
            }

            System.out.println("1. Tìm theo mã");
            System.out.println("2. Tìm theo tên");
            System.out.println("3. Tìm theo loại môn");
            int choice = InputUtil.getInt("Chọn: ");

            List<Subject> results = null;
            switch (choice) {
                case 1 -> {
                    String id = InputUtil.getNonEmptyString("Nhập mã: ");
                    results = subjects.stream()
                            .filter(t -> t.getSubjectID().equalsIgnoreCase(id))
                            .collect(Collectors.toList());
                }
                case 2 -> {
                    String name = InputUtil.getNonEmptyString("Nhập tên: ");
                    results = subjects.stream()
                            .filter(t -> t.getSubjectName().toLowerCase().contains(name.toLowerCase()))
                            .collect(Collectors.toList());
                }
                case 3 -> {
                    String subjectType = InputUtil.getNonEmptyString("Nhập loại môn: ");
                    results = subjects.stream()
                            .filter(t -> t.getSubjectType().equalsIgnoreCase(subjectType))
                            .collect(Collectors.toList());
                }
                default -> {
                    System.out.println("Lựa chọn không hợp lệ!");
                    pause();
                    return;
                }
            }

            if (results == null || results.isEmpty()) {
                System.out.println("Không tìm thấy môn học phù hợp!");
            } else {
                System.out.println("Kết quả tìm kiếm:");
                results.forEach(System.out::println);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }

        pause();
    }
}