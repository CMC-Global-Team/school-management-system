package Screen.Tuition;

import Models.Tuition;
import Screen.AbstractScreen;
import Services.TuitionService;
import Utils.InputUtil;

import java.util.List;
import java.util.Optional;

/**
 * SearchForTuition - Màn hình tra cứu thông tin học phí
 * Sử dụng TuitionService để quản lý dữ liệu thay vì trực tiếp đọc/ghi file
 */
public class SearchForTuition extends AbstractScreen {
    private final TuitionService tuitionService;

    public SearchForTuition() {
        this.tuitionService = TuitionService.getInstance();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Tra Cứu Thông Tin Học Phí        │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override

    public void handleInput() {
        String keyword = InputUtil.getNonEmptyString("Nhập mã học phí hoặc mã học sinh: ").toLowerCase();

        List<Tuition> results = tuitionService.searchTuitions(keyword);

        if (results.isEmpty()) {
            System.out.println("\nKhông tìm thấy thông tin học phí phù hợp.");
        } else {
            System.out.println("\nKẾT QUẢ TRA CỨU HỌC PHÍ:");
            System.out.println("─────────────────────────────────────────────");

            for (Tuition t : results) {
                System.out.println("→ Mã học phí: " + t.getTuitionId());
                System.out.println("  Mã học sinh: " + t.getStudentId());
                System.out.println("  Học kỳ: " + t.getSemester());
                System.out.println("  Năm học: " + t.getSchoolYear());
                System.out.println("  Số tiền thu: " + String.format("%,.0f", t.getAmount()) + " VND");
                System.out.println("  Ngày thu: " + t.getPaymentDate());
                System.out.println("  Phương thức: " + t.getMethod());
                System.out.println("  Trạng thái: " + t.getStatus());
                System.out.println("  Ghi chú: " + t.getNote());
                System.out.println("---------------------------------------------");
            }
        }

        InputUtil.pressEnterToContinue();
    }
}
