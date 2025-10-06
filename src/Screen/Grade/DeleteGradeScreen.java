package Screen.Grade;

import Screen.AbstractScreen;

public class DeleteGradeScreen extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           Xoá Điểm Học Sinh              │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang xoa diem cho Hoc Sinh dang duoc phat trien...");
        System.out.println("Cac thong tin se bao gom:");
        System.out.println("- Ma hoc sinh");
        System.out.println("- Ma diem");
        System.out.println("- Ma mon hoc");
        System.out.println("- Loai diem");
        System.out.println("- Diem Can Xoa");
        System.out.println("- Hoc ki");
        System.out.println("- Nam hoc");
        System.out.println("- Ghi chu");
        pause();
    }
}
