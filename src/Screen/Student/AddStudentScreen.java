package Screen.Student;

import Screen.AbstractScreen;
import Services.StudentService;
import Utils.InputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddStudentScreen extends AbstractScreen {

    private final StudentService studentService = StudentService.getInstance();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│             THÊM HỌC SINH MỚI               │");
        System.out.println("└──────────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String id = InputUtil.getString("Nhập mã học sinh: ").trim();

        // Kiểm tra ID trùng
        if (studentService.isStudentIdExists(id)) {
            System.out.println("⚠ Mã học sinh đã tồn tại! Vui lòng nhập mã khác.");
            return;
        }

        String name = InputUtil.getString("Nhập họ và tên: ");
        LocalDate dateOfBirth = null;

        // Nhập và kiểm tra ngày sinh
        while (dateOfBirth == null) {
            String dateStr = InputUtil.getString("Nhập ngày sinh (dd/MM/yyyy): ");
            try {
                dateOfBirth = LocalDate.parse(dateStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("⚠ Định dạng ngày không hợp lệ! Vui lòng nhập lại (dd/MM/yyyy).");
            }
        }

        String gender = InputUtil.getString("Nhập giới tính (Nam/Nữ): ");
        String className = InputUtil.getString("Nhập tên lớp: ");
        String course = InputUtil.getString("Nhập niên khóa (VD: 2023–2027): ");
        String parentPhone = InputUtil.getString("Nhập số điện thoại phụ huynh: ");
        String address = InputUtil.getString("Nhập địa chỉ: ");
        String status = InputUtil.getString("Nhập trạng thái (Đang học / Tạm nghỉ / Đã tốt nghiệp): ");

        // Gọi service để thêm học sinh
        boolean success = studentService.addStudent(
                id, name, dateOfBirth, gender, className, course,
                parentPhone, address, status
        );

        if (success) {
            System.out.println("Học sinh đã được thêm vào hệ thống.");
        } else {
            System.out.println("Thêm học sinh thất bại. Vui lòng thử lại!");
        }

        System.out.println("\nNhấn Enter để quay lại menu...");
        scanner.nextLine();
    }
}
