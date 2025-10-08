package Screen.Student;

import Screen.AbstractScreen;
import Services.StudentService;
import Utils.InputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import Models.Student;

public class UpdateStudentScreen extends AbstractScreen {

    private final StudentService studentService = StudentService.getInstance();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│           CẬP NHẬT THÔNG TIN HỌC SINH        │");
        System.out.println("└──────────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String id = InputUtil.getString("Nhập mã học sinh cần cập nhật: ").trim();

        // Tìm học sinh theo mã
        Optional<Student> optionalStudent = studentService.findById(id);
        if (optionalStudent.isEmpty()) {
            System.out.println("Không tìm thấy học sinh có mã '" + id + "'.");
            System.out.println("\nNhấn Enter để quay lại menu...");
            scanner.nextLine();
            return;
        }

        Student student = optionalStudent.get();

        // Hiển thị thông tin hiện tại
        System.out.println("\n--- Thông tin hiện tại ---");
        showCurrentInfo(student);

        System.out.println("\n--- Nhập thông tin mới (bỏ trống nếu giữ nguyên) ---");

        // Nhập các giá trị mới (cho phép bỏ trống)
        String name = InputUtil.getString("Họ và tên [" + student.getName() + "]: ").trim();
        if (!name.isEmpty()) student.setName(name);

        String dateStr = InputUtil.getString("Ngày sinh [" + student.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "] (dd/MM/yyyy): ").trim();
        if (!dateStr.isEmpty()) {
            try {
                LocalDate newDate = LocalDate.parse(dateStr, dateFormatter);
                student.setDateOfBirth(newDate);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }

        String gender = InputUtil.getString("Giới tính [" + student.getGender() + "]: ").trim();
        if (!gender.isEmpty()) student.setGender(gender);

        String className = InputUtil.getString("Lớp [" + student.getClassName() + "]: ").trim();
        if (!className.isEmpty()) student.setClassName(className);

        String course = InputUtil.getString("Niên khóa [" + student.getCourse() + "]: ").trim();
        if (!course.isEmpty()) student.setCourse(course);

        String parentPhone = InputUtil.getString("SĐT phụ huynh [" + student.getParentPhone() + "]: ").trim();
        if (!parentPhone.isEmpty()) student.setParentPhone(parentPhone);

        String address = InputUtil.getString("Địa chỉ [" + student.getAddress() + "]: ").trim();
        if (!address.isEmpty()) student.setAddress(address);

        String status = InputUtil.getString("Trạng thái [" + student.getStatus() + "]: ").trim();
        if (!status.isEmpty()) student.setStatus(status);

        // Gọi service cập nhật
        boolean success = studentService.updateStudent(student);

        if (success) {
            System.out.println("Cập nhật học sinh thành công!");
        } else {
            System.out.println("Cập nhật thất bại. Vui lòng thử lại!");
        }

        System.out.println("\nNhấn Enter để quay lại menu...");
        scanner.nextLine();
    }

    private void showCurrentInfo(Student student) {
        System.out.println("------------------------------------------");
        System.out.println("Mã học sinh : " + student.getId());
        System.out.println("Họ và tên   : " + student.getName());
        System.out.println("Ngày sinh   : " + student.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Giới tính   : " + student.getGender());
        System.out.println("Lớp         : " + student.getClassName());
        System.out.println("Niên khóa   : " + student.getCourse());
        System.out.println("SĐT phụ huynh: " + student.getParentPhone());
        System.out.println("Địa chỉ     : " + student.getAddress());
        System.out.println("Trạng thái  : " + student.getStatus());
        System.out.println("------------------------------------------");
    }
}
