package Screen.Tuition;

import Screen.AbstractScreen;

public class TuitionDiscountScreen extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Miễn Giảm Học Phí                │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang mien giam hoc phi dang duoc phat trien...");
        pause();
    }
}
