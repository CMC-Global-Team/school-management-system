package Screen.Teacher;

import Screen.AbstractScreen;

public class Teacherupdate extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           CẬP NHẬT GIÁO VIÊN             │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Cap Nhat Giao Vien dang duoc phat trien...");
        pause();
    }
}

