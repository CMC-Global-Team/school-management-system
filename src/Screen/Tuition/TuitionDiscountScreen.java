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
        // Nhập mã học phí — không cần đúng định dạng
        String tuitionId = InputUtil.getNonEmptyString("Nhập mã học phí cần miễn giảm: ");

        Optional<Tuition> optionalTuition = tuitionService.findById(tuitionId);

        if (optionalTuition.isEmpty()) {
            System.out.println(" Không tìm thấy mã học phí!");
            InputUtil.pressEnterToContinue();
            return;
        }

        Tuition t = optionalTuition.get();
        System.out.println("\nThông tin hiện tại:");
        System.out.println("Mã học sinh: " + t.getStudentId());
        System.out.println("Năm học: " + t.getSchoolYear());
        System.out.println("Số tiền thu: " + String.format("%,.0f", t.getAmount()) + " VND");
        System.out.println("Trạng thái: " + t.getStatus());

        double percent;
        while (true) {
            percent = InputUtil.getDouble("Nhập mức miễn giảm (%): ");
            if (percent < 0 || percent > 100) {
                System.out.println(" Mức miễn giảm phải trong khoảng 0 - 100!");
            } else {
                break;
            }
        }

        double discount = t.getAmount() * (percent / 100);
        double newAmount = t.getAmount() - discount;

        t.setAmount(newAmount);
        t.setNote("Được miễn giảm " + percent + "% học phí");

        if (tuitionService.updateTuition(t)) {
            System.out.println("Đã áp dụng miễn giảm học phí thành công!");
            System.out.println("Số tiền sau khi miễn giảm: " + String.format("%,.0f", newAmount) + " VND");
        } else {
            System.out.println(" Áp dụng miễn giảm thất bại!");
        }

        InputUtil.pressEnterToContinue();
    }
}
