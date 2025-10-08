package Screen.Tuition;

import Models.Tuition;
import Screen.AbstractScreen;
import Services.TuitionService;
import Utils.InputUtil;

import java.util.Optional;

/**
 * TuitionDiscountScreen - Màn hình miễn giảm học phí
 * Cho phép người dùng nhập mã học phí bất kỳ (không bắt buộc định dạng TFxx)
 */
public class TuitionDiscountScreen extends AbstractScreen {
    private final TuitionService tuitionService;

    public TuitionDiscountScreen() {
        this.tuitionService = TuitionService.getInstance();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Miễn Giảm Học Phí                │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String tuitionId = InputUtil.getNonEmptyString("Nhập mã học phí cần miễn giảm: ");
        double percent = -1;
        while (percent < 0 || percent > 100) {
            percent = InputUtil.getDouble("Nhập mức miễn giảm (%): ");
            if (percent < 0 || percent > 100) {
                System.out.println(" Mức miễn giảm phải trong khoảng 0 - 100!");
            }
        }

        // Gọi service để áp dụng miễn giảm
        tuitionService.applyDiscount(tuitionId, percent);

        InputUtil.pressEnterToContinue();
    }
}
