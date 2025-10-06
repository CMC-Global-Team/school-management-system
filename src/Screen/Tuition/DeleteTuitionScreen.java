package Screen.Tuition;

import Screen.AbstractScreen;

public class DeleteTuitionScreen extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Xoá Thông Tin Học Phí            │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang xoa thong tin hoc phi dang duoc phat trien...");
        pause();
    }
}
