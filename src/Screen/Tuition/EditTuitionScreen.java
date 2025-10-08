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
        String tuitionId = InputUtil.getNonEmptyString("Nhập mã học phí cần chỉnh sửa (TFxx): ");
        Optional<Tuition> optionalTuition = tuitionService.findById(tuitionId);

        if (optionalTuition.isEmpty()) {
            System.out.println("Không tìm thấy học phí với mã '" + tuitionId + "'!");
            InputUtil.pressEnterToContinue();
            return;
        }

        Tuition t = optionalTuition.get();

        System.out.println("\n--- Nhập thông tin mới (bỏ trống nếu không thay đổi) ---");
        String studentIdInput = InputUtil.getString("Mã sinh viên mới: ");
        String semesterInput = InputUtil.getString("Học kỳ mới: ");
        String schoolYearInput = InputUtil.getString("Năm học mới (VD: 2024-2028): ");
        String amountInput = InputUtil.getString("Số tiền thu mới: ");
        String paymentDateInput = InputUtil.getString("Ngày thu mới (dd/MM/yyyy, bỏ trống nếu không thay đổi): ");
        String statusInput = InputUtil.getString("Trạng thái mới (Đã thu/Chưa thu): ");
        String methodInput = InputUtil.getString("Phương thức mới (Tiền mặt/Chuyển khoản): ");
        String noteInput = InputUtil.getString("Ghi chú mới: ");

        if (tuitionService.updateTuition(t, studentIdInput, semesterInput, schoolYearInput,
                amountInput, paymentDateInput, statusInput, methodInput, noteInput)) {
            System.out.println("Đã cập nhật thông tin học phí thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }

        InputUtil.pressEnterToContinue();
    }
}