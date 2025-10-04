package Screen.Teacher;

import Models.Teacher;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddTeacherScreen extends AbstractScreen {

    // Mẫu số điện thoại cơ bản (10-11 số)
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10,11}");
    // Mẫu email
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           THÊM GIÁO VIÊN MỚI             │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {

        // Đọc danh sách giáo viên hiện tại từ file
        List<String> teacherLines = new ArrayList<>();
        try {
            if (FileUtil.fileExists("src/Data/teachers.txt")) {
                teacherLines = FileUtil.readLines("src/Data/teachers.txt");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file giáo viên: " + e.getMessage());
        }

        // Nhập mã giáo viên và kiểm tra trùng
        String id;
        while (true) {
            id = InputUtil.getNonEmptyString("Mã giáo viên: ");
            boolean exists = false;

            for (String line : teacherLines) {
                Teacher t = Teacher.fromString(line);
                if (t != null && t.getId().equalsIgnoreCase(id)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Mã giáo viên đã tồn tại! Nhập lại.");
            } else {
                break;
            }
        }

        // Nhập các thông tin khác
        String name = InputUtil.getNonEmptyString("Họ và tên: ");
        String subject = InputUtil.getNonEmptyString("Môn dạy: ");
        String degree = InputUtil.getNonEmptyString("Trình độ (Cử nhân/Thạc sĩ/Tiến sĩ): ");
        int experience = InputUtil.getInt("Số năm kinh nghiệm: ");

        // Email
        String email;
        while (true) {
            email = InputUtil.getNonEmptyString("Email: ");
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                System.out.println("Email không hợp lệ! Nhập lại.");
            } else {
                break;
            }
        }

        // Số điện thoại
        String phone;
        while (true) {
            phone = InputUtil.getNonEmptyString("Số điện thoại: ");
            if (!PHONE_PATTERN.matcher(phone).matches()) {
                System.out.println("Số điện thoại không hợp lệ! Nhập lại.");
            } else {
                break;
            }
        }

        String homeroom = InputUtil.getString("Lớp chủ nhiệm (Enter nếu chưa có): ");
        String status = InputUtil.getNonEmptyString("Trạng thái (Đang dạy/ Nghỉ hưu/ Công tác): ");

        // Tạo đối tượng Teacher
        Teacher newTeacher = new Teacher(id, name, status, subject, degree, experience, email, phone, homeroom);
        teacherLines.add(newTeacher.toString());

        // Lưu vào file
        try {
            FileUtil.writeLines("src/Data/teachers.txt", teacherLines);
            System.out.println("\nĐã thêm giáo viên thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
