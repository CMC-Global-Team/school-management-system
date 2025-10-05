package Screen.Tutition;

import Screen.AbstractScreen;

public class FinancialReportScreen extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Báo cáo tài chính                │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang bao cao tai chinh dang duoc phat trien...");
    }
}
