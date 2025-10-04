package Screen.Grade;

import Screen.AbstractScreen;

public class SearchForStudentGradesScreen extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           Tìm Kiếm Điểm Học Sinh         │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang tim Kiem diem cho Hoc Sinh dang duoc phat trien...");
        System.out.println("Cac thong tin se bao gom:");
       // System.out.println("- Ma lop");
        System.out.println("- Ma hoc sinh");
        System.out.println("- Ma mon hoc");
        System.out.println("- Hoc ki");
        System.out.println("- Nam hoc");
        pause();
    }
}
