package Screen.Tuition;

import Models.Tuition;
import Screen.AbstractScreen;
import Services.TuitionService;
import Utils.InputUtil;

import java.util.List;

/**
 * FinancialReportScreen - Màn hình báo cáo tài chính học phí
 * Sử dụng TuitionService để quản lý dữ liệu thay vì trực tiếp đọc/ghi file
 */
public class FinancialReportScreen extends AbstractScreen {
    private final TuitionService tuitionService;

    public FinancialReportScreen() {
        this.tuitionService = TuitionService.getInstance();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Báo cáo tài chính                │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String filterYear = InputUtil.getString("Nhập năm học để lọc (VD: 2024-2025, Enter để bỏ qua): ");
        TuitionService.TuitionReport report = tuitionService.generateFinancialReport(filterYear);

        System.out.println("Tổng học phí đã thu      : " + String.format("%,.0f", report.totalPaid) + " VND");
        System.out.println("Tổng học phí chưa thu    : " + String.format("%,.0f", report.totalUnpaid) + " VND");
        System.out.println("Tổng tiền miễn giảm      : " + String.format("%,.0f", report.totalDiscount) + " VND");
        System.out.println("Số học sinh đã đóng      : " + report.countPaid);
        System.out.println("Số học sinh chưa đóng    : " + report.countUnpaid);
        System.out.println("Doanh thu dự kiến        : " + String.format("%,.0f", report.expectedRevenue) + " VND");
        System.out.println("Doanh thu thực tế        : " + String.format("%,.0f", report.actualRevenue) + " VND");
        pause();
    }
}