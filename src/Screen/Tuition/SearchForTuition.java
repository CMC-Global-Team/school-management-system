package Screen.Tuition;

import Screen.AbstractScreen;

public class SearchForTuition extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Tra Cứu Thông Tin Học Phí        │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Tra cuu thong tin hoc phi dang duoc phat trien...");
        pause();
    }
}
