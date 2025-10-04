package Screen.Subject;

import Screen.AbstractScreen;
import Models.Subject;
import Utils.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";
    private List<Subject> subjectsList;

    public ListSubjectScreen() {
        loadSubject();
    }

    public void loadSubject() {
        subjectsList = new ArrayList<>();
        if (FileUtil.fileExists(FILE_PATH)) {
            try {
                List<String> lines = FileUtil.readLines(FILE_PATH);
                for (String line : lines) {
                    Subject t = Subject.fromString(line);
                    if (t != null) subjectsList.add(t);
                }
            } catch (IOException e) {
                System.err.println("Lỗi khi đọc file môn học: " + e.getMessage());
            }
        }
    }

    @Override
    public void display() {
        loadSubject();
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│             DANH SÁCH MÔN HỌC            │");
        System.out.println("└──────────────────────────────────────────┘");

        if (subjectsList.isEmpty()) {
            System.out.println("Chưa có môn học nào trong hệ thống.");
            return;
        }

        System.out.printf("%-15s %-20s %-10s %-10s %-20s %-20s %-25s %-15s%n",
                "Mã môn học", "Tên môn học ", "Số tiết", "Hệ số", "Loại môn", "Giáo viên phụ trách", "Mô tả", "Trạng thái");
        System.out.println("-".repeat(50));

        for (Subject s : subjectsList) {
            System.out.printf("%-15s %-20s %-10d %-10f %-20s %-20s %-25s %-15s%n",
                    s.getSubjectID(), s.getSubjectName(), s.getLessonCount(),
                    s.getConfficient(), s.getSubjectType(), s.getTeacherInCharge(),
                    s.getDescription(), s.getStatus());
            System.out.println("-".repeat(50));
        }
    }

    @Override
    public void handleInput() {
        System.out.println("\nNhấn Enter để quay lại...");
        scanner.nextLine();
    }
}
