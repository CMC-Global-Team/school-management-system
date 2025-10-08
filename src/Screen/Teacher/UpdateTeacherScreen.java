package Screen.Teacher;

import Models.Subject;
import Models.Teacher;
import Screen.AbstractScreen;
import Services.SubjectService;
import Services.TeacherService;
import Utils.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpdateTeacherScreen extends AbstractScreen {

    private final TeacherService teacherService;
    private final SubjectService subjectService;

    public UpdateTeacherScreen() {
        this.teacherService = TeacherService.getInstance();
        this.subjectService = SubjectService.getInstance();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│       CẬP NHẬT THÔNG TIN GIÁO VIÊN      │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String id = InputUtil.getNonEmptyString("Nhập mã giáo viên cần cập nhật: ");

        Teacher existing = teacherService.findById(id).orElse(null);

        if (existing == null) {
            System.out.println("Không tìm thấy giáo viên có mã '" + id + "'.");
            pause();
            return;
        }

        System.out.println("\nThông tin hiện tại:");
        System.out.println(existing);

        System.out.println("\n--- Nhập thông tin mới (Enter để giữ nguyên) ---");

        String name = InputUtil.getString("Tên mới (" + existing.getName() + "): ");
        if (!name.isEmpty()) existing.setName(name);

        // --- Cập nhật môn học theo mã ---
        System.out.println("\nDanh sách môn học hiện có:");
        List<Subject> allSubjects = subjectService.getAllSubjects();
        for (Subject s : allSubjects) {
            System.out.printf("%-10s %-25s %-10d%n",
                    s.getSubjectID(), s.getSubjectName(), s.getLessonCount());
        }

        String subjectInput = InputUtil.getString("\nNhập mã môn (hiện tại: " + String.join(", ", existing.getTeacherSubjects()) + "): ");
        if (!subjectInput.isEmpty()) {
            List<String> teacherSubjects = new ArrayList<>();

            String[] ids = subjectInput.split(",");
            for (String sid : ids) {
                sid = sid.trim();
                try {
                    Optional<Subject> subjectOpt = subjectService.findById(sid);
                    if (subjectOpt.isPresent()) {
                        teacherSubjects.add(subjectOpt.get().getSubjectName());
                    } else {
                        System.out.println("Mã môn không tồn tại: " + sid);
                    }
                } catch (Exception e) {
                    System.out.println("Lỗi khi kiểm tra mã môn '" + sid + "': " + e.getMessage());
                }
            }

            if (!teacherSubjects.isEmpty()) {
                existing.setTeacherSubjects(teacherSubjects);
            } else {
                System.out.println("Không có mã môn hợp lệ, giữ nguyên danh sách môn cũ!");
            }
        }

        // --- Trình độ ---
        System.out.println("\nTrình độ hiện tại: " + existing.getTeacherDegree());
        System.out.println("0 - Cử nhân");
        System.out.println("1 - Thạc sĩ");
        System.out.println("2 - Tiến sĩ");
        String degreeInput = InputUtil.getString("Nhập mã trình độ (Enter để giữ nguyên): ");
        if (!degreeInput.isEmpty()) {
            try {
                int code = Integer.parseInt(degreeInput);
                switch (code) {
                    case 0 -> existing.setTeacherDegree("Cử nhân");
                    case 1 -> existing.setTeacherDegree("Thạc sĩ");
                    case 2 -> existing.setTeacherDegree("Tiến sĩ");
                    default -> System.out.println("Mã không hợp lệ, giữ nguyên trình độ cũ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ, giữ nguyên trình độ cũ!");
            }
        }

        // --- Kinh nghiệm ---
        String expStr = InputUtil.getString("Kinh nghiệm (" + existing.getTeacherExperience() + "): ");
        if (!expStr.isEmpty()) {
            try {
                existing.setTeacherExperience(Integer.parseInt(expStr));
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không hợp lệ, giữ nguyên kinh nghiệm cũ!");
            }
        }

        // --- Email ---
        String email = InputUtil.getString("Email (" + existing.getTeacherEmail() + "): ");
        if (!email.isEmpty()) {
            while (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Email không hợp lệ! Vui lòng nhập lại hoặc Enter để giữ nguyên.");
                email = InputUtil.getString("Email (" + existing.getTeacherEmail() + "): ");
                if (email.isEmpty()) break;
            }
            if (!email.isEmpty()) existing.setTeacherEmail(email);
        }

        // --- SĐT ---
        String phone = InputUtil.getString("SĐT (" + existing.getTeacherPhone() + "): ");
        if (!phone.isEmpty()) {
            while (!phone.matches("\\d{10}")) {
                System.out.println("SĐT không hợp lệ! Phải đủ 10 chữ số hoặc Enter để bỏ qua.");
                phone = InputUtil.getString("SĐT (" + existing.getTeacherPhone() + "): ");
                if (phone.isEmpty()) break;
            }
            if (!phone.isEmpty()) existing.setTeacherPhone(phone);
        }

        String homeroom = InputUtil.getString("Lớp CN (" + existing.getTeacherHomeroom() + "): ");
        if (!homeroom.isEmpty()) existing.setTeacherHomeroom(homeroom);

        // --- Trạng thái ---
        System.out.println("\nTrạng thái hiện tại: " + existing.getStatus());
        System.out.println("0 - Đang dạy");
        System.out.println("1 - Nghỉ hưu");
        System.out.println("2 - Công tác");
        String statusInput = InputUtil.getString("Nhập mã trạng thái (Enter để giữ nguyên): ");
        if (!statusInput.isEmpty()) {
            try {
                int code = Integer.parseInt(statusInput);
                switch (code) {
                    case 0 -> existing.setStatus("Đang dạy");
                    case 1 -> existing.setStatus("Nghỉ hưu");
                    case 2 -> existing.setStatus("Công tác");
                    default -> System.out.println("Mã trạng thái không hợp lệ, giữ nguyên!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai định dạng, giữ nguyên!");
            }
        }

        // --- Gọi service cập nhật ---
        boolean success = teacherService.updateTeacher(existing);
        if (success) {
            System.out.println("\nCập nhật giáo viên thành công!");
        } else {
            System.out.println("\nCó lỗi xảy ra khi cập nhật giáo viên!");
        }

        pause();
    }
}
