package Screen.Student;

import Models.Student;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddStudentScreen extends AbstractScreen {

    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10,11}");
    private static final Pattern GENDER_PATTERN = Pattern.compile("(?i)(nam|nữ|nu)");

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│             THÊM HỌC SINH MỚI            │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {

        List<String> studentLines = new ArrayList<>();
        try {
            if (FileUtil.fileExists("src/Data/students.txt")) {
                studentLines = FileUtil.readLines("src/Data/students.txt");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file học sinh: " + e.getMessage());
        }

        String id;
        while (true) {
            id = InputUtil.getNonEmptyString("Mã học sinh: ");
            boolean exists = false;

            for (String line : studentLines) {
                Student s = Student.fromString(line);
                if (s != null && s.getId().equalsIgnoreCase(id)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Mã học sinh đã tồn tại! Vui lòng nhập mã khác.");
            } else break;
        }

        String name = InputUtil.getNonEmptyString("Họ và tên: ");

        LocalDate dateOfBirth;
        while (true) {
            String dobStr = InputUtil.getNonEmptyString("Ngày sinh (yyyy-MM-dd): ");
            try {
                dateOfBirth = LocalDate.parse(dobStr);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không hợp lệ! Vui lòng nhập lại (vd: 2008-03-15).");
            }
        }

        String gender;
        while (true) {
            gender = InputUtil.getNonEmptyString("Giới tính (Nam/Nữ): ");
            if (!GENDER_PATTERN.matcher(gender).matches()) {
                System.out.println("Chỉ được nhập Nam hoặc Nữ!");
            } else break;
        }

        String className = InputUtil.getNonEmptyString("Lớp học: ");
        String course = InputUtil.getNonEmptyString("Niên khóa: ");

        String parentPhone;
        while (true) {
            parentPhone = InputUtil.getNonEmptyString("Số điện thoại phụ huynh: ");
            if (!PHONE_PATTERN.matcher(parentPhone).matches()) {
                System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập lại (10-11 số).");
            } else break;
        }

        String address = InputUtil.getNonEmptyString("Địa chỉ: ");
        String status = InputUtil.getNonEmptyString("Trạng thái (Đang học / Đã tốt nghiệp / Bảo lưu): ");

        // Tạo đối tượng Student
        Student newStudent = new Student(id, name, dateOfBirth, gender, className, course, parentPhone, address, status);

        // Thêm vào danh sách
        studentLines.add(newStudent.toString());

        // Ghi file
        try {
            FileUtil.writeLines("src/Data/students.txt", studentLines);
            System.out.println("\nĐã thêm học sinh thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
