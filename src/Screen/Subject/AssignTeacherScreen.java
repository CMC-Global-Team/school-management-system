package Screen.Subject;

import Models.Subject;
import Models.Teacher;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignTeacherScreen extends AbstractScreen {

    private static final String TEACHER_FILE = "src/Data/teachers.txt";
    private static final String SUBJECT_FILE = "src/Data/subjects.txt";
    private static final String ASSIGN_FILE = "src/Data/assigned_teachers.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│       GÁN GIÁO VIÊN CHO MÔN HỌC         │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        List<String> teacherLines = new ArrayList<>();
        List<String> subjectLines = new ArrayList<>();
        List<String> assignLines = new ArrayList<>();

        try {
            if (FileUtil.fileExists(TEACHER_FILE))
                teacherLines = FileUtil.readLines(TEACHER_FILE);
            if (FileUtil.fileExists(SUBJECT_FILE))
                subjectLines = FileUtil.readLines(SUBJECT_FILE);
            if (FileUtil.fileExists(ASSIGN_FILE))
                assignLines = FileUtil.readLines(ASSIGN_FILE);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
            return;
        }

        // Nếu chưa có dữ liệu
        if (teacherLines.isEmpty()) {
            System.out.println("⚠️  Chưa có giáo viên nào trong hệ thống!");
            InputUtil.pressEnterToContinue();
            return;
        }
        if (subjectLines.isEmpty()) {
            System.out.println("⚠️  Chưa có môn học nào trong hệ thống!");
            InputUtil.pressEnterToContinue();
            return;
        }

        // Nhập mã giáo viên
        String teacherId = null;
        while (true) {
            String inputId = InputUtil.getNonEmptyString("Nhập mã giáo viên: ");
            final String teacherIdFinal = inputId;
            boolean exists = teacherLines.stream()
                    .map(Teacher::fromString)
                    .anyMatch(t -> t != null && t.getId().equalsIgnoreCase(teacherIdFinal));
            if (exists){
                teacherId = teacherIdFinal;
                break;
            }
            System.out.println("Mã giáo viên không tồn tại! Vui lòng nhập lại.");
        }

        // Nhập mã môn học
        String subjectId =  null;
        while (true) {
            subjectId = InputUtil.getNonEmptyString("Nhập mã môn học: ");
            final String subjectIdFinal = subjectId;
            boolean exists = subjectLines.stream()
                    .map(Subject::fromString)
                    .anyMatch(s -> s != null && s.getSubjectID().equalsIgnoreCase(subjectIdFinal));
            if (exists) {
                subjectId = subjectIdFinal;
                break;
            }
            System.out.println("Mã môn học không tồn tại! Vui lòng nhập lại.");
        }
        final String teacherIdFinal = teacherId;
        final String subjectIdFinal = subjectId;
        // Kiểm tra trùng gán
        boolean alreadyAssigned = assignLines.stream()
                .anyMatch(line -> line.equalsIgnoreCase(teacherIdFinal + "," + subjectIdFinal));

        if (alreadyAssigned) {
            System.out.println("Giáo viên này đã được gán cho môn học đó rồi!");
            InputUtil.pressEnterToContinue();
            return;
        }

        // Thêm dòng gán mới
        assignLines.add(teacherId + "," + subjectId);

        try {
            FileUtil.writeLines(ASSIGN_FILE, assignLines);
            System.out.println("Gán giáo viên cho môn học thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
