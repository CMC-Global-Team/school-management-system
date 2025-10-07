package Screen.Teacher;

import Models.Teacher;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTeacherScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/teachers.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           TÌM KIẾM GIÁO VIÊN             │");
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

            System.out.println("1. Tìm theo mã");
            System.out.println("2. Tìm theo tên");
            System.out.println("3. Tìm theo môn dạy");
            int choice = InputUtil.getInt("Chọn: ");

            List<Teacher> results = null;
            switch (choice) {
                case 1 -> {
                    String id = InputUtil.getNonEmptyString("Nhập mã: ");
                    results = teachers.stream()
                            .filter(t -> t.getId().equalsIgnoreCase(id))
                            .collect(Collectors.toList());
                }
                case 2 -> {
                    String name = InputUtil.getNonEmptyString("Nhập tên: ");
                    results = teachers.stream()
                            .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                            .collect(Collectors.toList());
                }
                case 3 -> {
                    String subject = InputUtil.getNonEmptyString("Nhập môn dạy: ");
                    results = teachers.stream()
                            .filter(t -> t.getTeacherSubject().equalsIgnoreCase(subject))
                            .collect(Collectors.toList());
                }
                default -> {
                    System.out.println("Lựa chọn không hợp lệ!");
                    pause();
                    return;
                }
            }

            if (results == null || results.isEmpty()) {
                System.out.println("Không tìm thấy giáo viên phù hợp!");
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
