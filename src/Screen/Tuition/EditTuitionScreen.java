package Screen.Tuition;

import Models.Tuition;
import Screen.AbstractScreen;
import Services.TuitionService;
import Utils.InputUtil;

import java.time.LocalDate;
import java.util.Optional;

/**
 * EditTuitionScreen - Màn hình chỉnh sửa thông tin học phí
 * Sử dụng TuitionService để quản lý dữ liệu thay vì trực tiếp đọc/ghi file
 */
public class EditTuitionScreen extends AbstractScreen {
    private final TuitionService tuitionService;

    public EditTuitionScreen() {
        this.tuitionService = TuitionService.getInstance();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Sửa Thông Tin Học Phí            │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        // Nhập mã học phí cần chỉnh sửa
        String tuitionId = InputUtil.getNonEmptyString("Nhập mã học phí cần chỉnh sửa (TFxx): ");
        Optional<Tuition> optionalTuition = tuitionService.findById(tuitionId);

        if (optionalTuition.isEmpty()) {
            System.out.println("Không tìm thấy học phí với mã '" + tuitionId + "'!");
            InputUtil.pressEnterToContinue();
            return;
        }

        Tuition t = optionalTuition.get();
        System.out.println("\n Thông tin hiện tại:");
        System.out.println("Mã sinh viên: " + t.getStudentId());
        System.out.println("Học kỳ: " + t.getSemester());
        System.out.println("Năm học: " + t.getSchoolYear());
        System.out.println("Số tiền thu: " + String.format("%,.0f", t.getAmount()) + " VND");
        System.out.println("Ngày thu: " + t.getPaymentDate());
        System.out.println("Phương thức: " + t.getMethod());
        System.out.println("Trạng thái: " + t.getStatus());
        System.out.println("Ghi chú: " + t.getNote());

        System.out.println("\n--- Nhập thông tin mới (bỏ trống nếu không thay đổi) ---");

        // Cập nhật từng trường
        String studentIdInput = InputUtil.getString("Mã sinh viên mới: ");
        if (!studentIdInput.isEmpty()) {
            t.setStudentId(studentIdInput);
        }

        String semesterInput = InputUtil.getString("Học kỳ mới: ");
        if (!semesterInput.isEmpty()) {
            try {
                t.setSemester(Integer.parseInt(semesterInput));
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }

        String schoolYearInput = InputUtil.getString("Năm học mới (VD: 2024-2028): ");
        if (!schoolYearInput.isEmpty()) {
            while (!schoolYearInput.matches("^\\d{4}-\\d{4}$")) {
                schoolYearInput = InputUtil.getString("Sai định dạng! Nhập lại (VD: 2024-2028): ");
            }
            t.setSchoolYear(schoolYearInput);
        }

        String amountInput = InputUtil.getString("Số tiền thu mới: ");
        if (!amountInput.isEmpty()) {
            try {
                double money = Double.parseDouble(amountInput);
                if (money < 0) {
                    System.out.println(" Số tiền không hợp lệ, giữ nguyên giá trị cũ.");
                } else {
                    t.setAmount(money);
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ, giữ nguyên giá trị cũ.");
            }
        }


        // --- Nhập ngày thu mới ---
        LocalDate newPaymentDate = null;
        while (newPaymentDate == null) {
            // Sử dụng hàm getDate của InputUtil
            newPaymentDate = InputUtil.getDate("Ngày thu mới");

            // Kiểm tra ngày không vượt quá hiện tại
            if (newPaymentDate.isAfter(LocalDate.now())) {
                System.out.println(" Ngày thu không được vượt quá ngày hiện tại!");
                newPaymentDate = null; // reset để yêu cầu nhập lại
            }
        }

// Cập nhật vào Tuition
        t.setPaymentDate(newPaymentDate);

        String methodInput = InputUtil.getString("Phương thức mới (Tiền mặt/Chuyển khoản): ");
        if (!methodInput.isEmpty()) {
            while (!methodInput.equalsIgnoreCase("Tiền mặt") && !methodInput.equalsIgnoreCase("Chuyển khoản")) {
                methodInput = InputUtil.getString(" Chỉ được nhập 'Tiền mặt' hoặc 'Chuyển khoản': ");
            }
            t.setMethod(methodInput);
        }

        String statusInput = InputUtil.getString("Trạng thái mới (Đã thu/Chưa thu): ");
        if (!statusInput.isEmpty()) {
            while (!statusInput.equalsIgnoreCase("Đã thu") && !statusInput.equalsIgnoreCase("Chưa thu")) {
                statusInput = InputUtil.getString(" Chỉ được nhập 'Đã thu' hoặc 'Chưa thu': ");
            }
            t.setStatus(statusInput);
        }

        String noteInput = InputUtil.getString("Ghi chú mới: ");
        if (!noteInput.isEmpty()) {
            t.setNote(noteInput);
        }

        // Cập nhật qua service
        if (tuitionService.updateTuition(t)) {
            System.out.println(" Đã cập nhật thông tin học phí thành công!");
        } else {
            System.out.println(" Cập nhật thất bại!");
        }

        InputUtil.pressEnterToContinue();
    }
}