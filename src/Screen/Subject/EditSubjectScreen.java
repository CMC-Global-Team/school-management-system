package Screen.Subject;


import Models.Subject;
import Screen.AbstractScreen;
import Services.SubjectService;
import Utils.InputUtil;


import java.util.Optional;


public class EditSubjectScreen extends AbstractScreen {


    private final SubjectService subjectService;


    public EditSubjectScreen() {
        this.subjectService = SubjectService.getInstance();
    }


    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│          CHỈNH SỬA THÔNG TIN MÔN HỌC        │");
        System.out.println("└──────────────────────────────────────────────┘");
    }


    @Override
    public void handleInput() {
        // Nhập mã môn học cần chỉnh sửa
        String subjectId = InputUtil.getNonEmptyString("Nhập mã môn học cần chỉnh sửa: ");
        Optional<Subject> optionalSubject = subjectService.findById(subjectId);


        if (optionalSubject.isEmpty()) {
            System.out.println("Không tìm thấy môn học với mã '" + subjectId + "'!");
            InputUtil.pressEnterToContinue();
            return;
        }


        Subject s = optionalSubject.get();
        System.out.println("\n🔎 Thông tin hiện tại:");
        System.out.println("Tên môn học: " + s.getSubjectName());
        System.out.println("Số tiết: " + s.getLessonCount());
        System.out.println("Hệ số: " + s.getConfficient());
        System.out.println("Loại môn: " + s.getSubjectType());
        System.out.println("Mô tả: " + s.getDescription());
        System.out.println("Giáo viên phụ trách: " + s.getTeacherInCharge());
        System.out.println("Trạng thái: " + s.getStatus());


        System.out.println("\n--- Nhập thông tin mới (bỏ trống nếu không thay đổi) ---");


        // Cập nhật từng trường
        String nameInput = InputUtil.getString("Tên môn học mới: ");
        if (!nameInput.isEmpty()) s.setSubjectName(nameInput);


        String lessonsInput = InputUtil.getString("Số tiết mới: ");
        if (!lessonsInput.isEmpty()) {
            try {
                s.setLessonCount(Integer.parseInt(lessonsInput));
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }


        String coeffInput = InputUtil.getString("Hệ số mới: ");
        if (!coeffInput.isEmpty()) {
            try {
                s.setConfficient(Double.parseDouble(coeffInput));
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }


        String typeInput = InputUtil.getString("Loại môn mới: ");
        if (!typeInput.isEmpty()) s.setSubjectType(typeInput);


        String descInput = InputUtil.getString("Mô tả mới: ");
        if (!descInput.isEmpty()) s.setDescription(descInput);


        String teacherInput = InputUtil.getString("Giáo viên phụ trách mới: ");
        if (!teacherInput.isEmpty()) s.setTeacherInCharge(teacherInput);


        String statusInput = InputUtil.getString("Trạng thái mới: ");
        if (!statusInput.isEmpty()) s.setStatus(statusInput);


        // Cập nhật qua service
        if (subjectService.updateSubject(s)) {
            System.out.println("✓ Đã cập nhật thông tin môn học thành công!");
        } else {
            System.out.println("✗ Cập nhật thất bại!");
        }


        InputUtil.pressEnterToContinue();
    }
}
