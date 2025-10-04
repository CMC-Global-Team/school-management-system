package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│        CẬP NHẬT THÔNG TIN MÔN HỌC        │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        try {
            List<String> lines = FileUtil.readLines(FILE_PATH);
            List<Subject> teachers = lines.stream()
                    .map(Subject::fromString)
                    .collect(Collectors.toList());

            if (teachers.isEmpty()) {
                System.out.println("Hiện chưa có môn học nào trong hệ thống!");
                pause();
                return;
            }

            String id = InputUtil.getNonEmptyString("Nhập mã môn học cần cập nhật: ");
            Subject s = teachers.stream()
                    .filter(x -> x.getSubjectID().equalsIgnoreCase(id))
                    .findFirst()
                    .orElse(null);

            if (s == null) {
                System.out.println("Không tìm thấy môn học với mã này!");
                pause();
                return;
            }

            System.out.println("Thông tin hiện tại: " + s);

            String name = InputUtil.getString("Tên mới (" + s.getSubjectName() + "): ");
            if (!name.isEmpty()) s.setSubjectName(name);

            String lessonCount = InputUtil.getString("Số tiết học (" + s.getLessonCount() + "): ");
            if (!lessonCount.isEmpty()) {
                try {
                    s.setLessonCount(Integer.parseInt(lessonCount));
                } catch (NumberFormatException e) {
                    System.out.println("Số tiết học không hợp lệ, giữ nguyên giá trị cũ!");
                }
            }

            String confficient = InputUtil.getString("Hệ số (" + s.getSubjectName() + "): ");
            if (!confficient.isEmpty()) {
                try {
                    s.setConfficient(Double.parseDouble(lessonCount));
                } catch (NumberFormatException e) {
                    System.out.println("Hệ số không hợp lệ, giữ nguyên giá trị cũ!");
                }
            }

            String subjectType = InputUtil.getString("Loại môn (" + s.getSubjectName() + "): ");
            if (!subjectType.isEmpty()) s.setSubjectType(subjectType);

            String description = InputUtil.getString("Mô tả (" + s.getDescription() + "): ");
            if (!description.isEmpty()) s.setDescription(description);

            String teacherInCharge = InputUtil.getString("Giáo viên phụ trách (" + s.getSubjectName() + "): ");
            if (!teacherInCharge.isEmpty()) s.setDescription(teacherInCharge);

            String status = InputUtil.getString("Trạng thái (" + s.getSubjectName() + "): ");
            if (!status.isEmpty()) s.setStatus(status);

            // Lưu lại file
            List<String> newLines = teachers.stream().map(Subject::toString).collect(Collectors.toList());
            FileUtil.writeLines(FILE_PATH, newLines);

            System.out.println("Cập nhật thông tin môn học thành công!");
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc/ghi file: " + e.getMessage());
        }

        pause();
    }
}
