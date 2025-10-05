package Screen.Tutition;

import Screen.AbstractScreen;

public class EditTuitionScreen extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│         Sửa Thông Tin Học Phí            │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang sua thong tin hoc phi dang duoc phat trien...");
        System.out.println("Cac thong tin se bao gom:");
        System.out.println("- Ma hoc sinh");
        System.out.println("- Ma lop");
        System.out.println("- Ho ten hoc sinh");
        System.out.println("- Nam hoc");
        System.out.println("- So tien thu");
        System.out.println("- Ngay thu");
        System.out.println("- Phuong thuc");
        System.out.println("- Trang thai");
        System.out.println("- Ghi chu");
    }
}
