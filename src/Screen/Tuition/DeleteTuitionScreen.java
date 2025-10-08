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
        String tuitionId = InputUtil.getNonEmptyString("Nhập mã học phí cần xóa (ví dụ: TF0001): ");

        // Gọi service để xóa, service sẽ xử lý validate và thông báo
        tuitionService.removeTuition(tuitionId);

        InputUtil.pressEnterToContinue();
    }
}
