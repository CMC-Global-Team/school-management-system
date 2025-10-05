package Screen.Tuition;

import Screen.AbstractScreen;

public class RecordTuitionScreen extends AbstractScreen {
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│       Ghi nhận thanh toán học phí        │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang ghi nhan thanh toan dang duoc phat trien...");
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

        pause();
    }
}
