package Screen.Tuition;

import Models.Tuition;
import Screen.AbstractScreen;
import Services.TuitionService;
import Utils.InputUtil;

import java.util.Optional;

/**
 * DeleteTuitionScreen - Màn hình xóa thông tin học phí
 * Sử dụng TuitionService để quản lý dữ liệu thay vì trực tiếp đọc/ghi file
 */
public class DeleteTuitionScreen extends AbstractScreen {
    private final TuitionService tuitionService;

    public DeleteTuitionScreen() {
        this.tuitionService = TuitionService.getInstance();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Xoá Thông Tin Học Phí            │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String tuitionId;

        // Nhập mã học phí phải đúng định dạng TF + 4 số
        while (true) {
            tuitionId = InputUtil.getNonEmptyString("Nhập mã học phí cần xóa (ví dụ: TF0001): ");
            if (!tuitionId.matches("^TF\\d{4}$")) {
                System.out.println(" Mã học phí phải viết hoa dạng TFxxxx (ví dụ: TF0001).");
                continue;
            }
            break;
        }

        Optional<Tuition> optionalTuition = tuitionService.findById(tuitionId);

        if (optionalTuition.isEmpty()) {
            System.out.println(" Không tìm thấy học phí với mã: " + tuitionId);
            InputUtil.pressEnterToContinue();
            return;
        }

        Tuition t = optionalTuition.get();
        System.out.println("→ Xóa học phí của học sinh: " + t.getStudentId() +
                " | Số tiền: " + String.format("%,.0f", t.getAmount()) + " VND");

        // Gọi service để xóa
        if (tuitionService.deleteTuition(tuitionId)) {
            System.out.println(" Xóa học phí thành công!");
        } else {
            System.out.println(" Xóa thất bại!");
        }

        InputUtil.pressEnterToContinue();
    }
}
