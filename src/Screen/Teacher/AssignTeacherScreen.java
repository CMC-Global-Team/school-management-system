package Screen.Teacher;

import Models.Teacher;
import Models.TeachingAssignment;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class AssignTeacherScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("│       PHÂN CÔNG GIẢNG DẠY CHO GIÁO VIÊN   │");
        System.out.println("└────────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {

        List<String> teacherLines;
        try {
            teacherLines = FileUtil.readLines("src/Data/teachers.txt");
        } catch (Exception e) {
            System.out.println("Lỗi đọc file giáo viên: " + e.getMessage());
            return;
        }

        if (teacherLines.isEmpty()) {
            System.out.println("Chưa có giáo viên nào trong hệ thống!");
            return;
        }

        System.out.println("\nDanh sách giáo viên:");
        for (String line : teacherLines) {
            Teacher t = Teacher.fromString(line);
            if (t != null)
                System.out.printf("ID: %-8s | Tên: %-20s | Môn: %-15s | Lớp CN: %s%n",
                        t.getId(), t.getName(), t.getTeacherSubject(), t.getTeacherHomeroom());
        }

        String teacherId = InputUtil.getNonEmptyString("\nNhập mã giáo viên cần phân công: ");
        Teacher selectedTeacher = null;
        for (String line : teacherLines) {
            Teacher t = Teacher.fromString(line);
            if (t != null && t.getId().equalsIgnoreCase(teacherId)) {
                selectedTeacher = t;
                break;
            }
        }

        if (selectedTeacher == null) {
            System.out.println("Không tìm thấy giáo viên với mã: " + teacherId);
            return;
        }

        String subject = InputUtil.getNonEmptyString("Nhập môn giảng dạy: ");
        String className = InputUtil.getNonEmptyString("Nhập lớp giảng dạy: ");

        TeachingAssignment assignment = new TeachingAssignment(
                selectedTeacher.getId(),
                selectedTeacher.getName(),
                subject,
                className
        );

        // Đọc file phân công cũ + thêm mới
        List<String> assignmentLines = new ArrayList<>();
        try {
            if (FileUtil.fileExists("src/Data/teacher_assignments.txt")) {
                assignmentLines = FileUtil.readLines("src/Data/teacher_assignments.txt");
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file phân công: " + e.getMessage());
        }

        assignmentLines.add(assignment.toString());

        // Lưu lại file
        try {
            FileUtil.writeLines("src/Data/teacher_assignments.txt", assignmentLines);
            System.out.println("\nĐã phân công giảng dạy thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
