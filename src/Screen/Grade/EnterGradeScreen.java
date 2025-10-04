package Screen.Grade;
import Screen.AbstractScreen;
public class EnterGradeScreen extends  AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           Nhập Điểm Cho Học Sinh         │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang nhap diem cho Hoc Sinh dang duoc phat trien...");
        System.out.println("Cac thong tin se bao gom:");
        System.out.println("- Ma hoc sinh");
        System.out.println("- Ma diem");
        System.out.println("- Ma mon hoc");
        System.out.println("- Loai diem");
        System.out.println("- So diem dat duoc");
        System.out.println("- Hoc ki");
        System.out.println("- Nam hoc");
        System.out.println("- Ngay nhap");
        System.out.println("- Ghi chu");
        pause();
    }
}
