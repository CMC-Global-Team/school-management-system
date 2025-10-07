package Screen.Teacher;

import Models.Teacher;
import Screen.AbstractScreen;
import Services.TeacherService;
import Utils.InputUtil;

/**
 * Màn hình cập nhật thông tin giáo viên
 * - Hiển thị giao diện nhập
 * - Gọi TeacherService để xử lý logic cập nhật
 */
public class UpdateTeacherScreen extends AbstractScreen {

    private final TeacherService teacherService;

    public UpdateTeacherScreen() {
        this.teacherService = TeacherService.getInstance(); // sử dụng Singleton
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

        // Tìm giáo viên theo ID trước
        Teacher existing = teacherService.findById(id).orElse(null);

        if (existing == null) {
            System.out.println("⚠ Không tìm thấy giáo viên có mã '" + id + "'.");
            pause();
            return;
        }

        System.out.println("\nThông tin hiện tại:");
        System.out.println(existing);

        System.out.println("\nNhập thông tin mới (Enter để giữ nguyên):");

        String name = InputUtil.getString("Tên mới (" + existing.getName() + "): ");
        if (!name.isEmpty()) existing.setName(name);

        String subject = InputUtil.getString("Môn dạy (" + existing.getTeacherSubject() + "): ");
        if (!subject.isEmpty()) existing.setTeacherSubject(subject);

        String degree = InputUtil.getString("Học vị (" + existing.getTeacherDegree() + "): ");
        if (!degree.isEmpty()) existing.setTeacherDegree(degree);

        String expStr = InputUtil.getString("Kinh nghiệm (" + existing.getTeacherExperience() + "): ");
        if (!expStr.isEmpty()) {
            try {
                existing.setTeacherExperience(Integer.parseInt(expStr));
            } catch (NumberFormatException e) {
                System.out.println("⚠ Kinh nghiệm không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }

        String email = InputUtil.getString("Email (" + existing.getTeacherEmail() + "): ");
        if (!email.isEmpty()) existing.setTeacherEmail(email);

        String phone = InputUtil.getString("SĐT (" + existing.getTeacherPhone() + "): ");
        if (!phone.isEmpty()) existing.setTeacherPhone(phone);

        String homeroom = InputUtil.getString("Lớp CN (" + existing.getTeacherHomeroom() + "): ");
        if (!homeroom.isEmpty()) existing.setTeacherHomeroom(homeroom);

        // Gọi service để cập nhật
        boolean success = teacherService.updateTeacher(existing);

        if (success) {
            System.out.println("✅ Cập nhật giáo viên thành công!");
        } else {
            System.out.println("❌ Có lỗi xảy ra khi cập nhật giáo viên!");
        }

        pause();
    }
}
