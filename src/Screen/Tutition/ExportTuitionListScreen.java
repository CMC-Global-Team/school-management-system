package Screen.Tutition;

import Screen.AbstractScreen;

public class ExportTuitionListScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│        Xuất Danh Sách Học Phí            │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Xuất danh sách học phí dang duoc phat trien...");
        System.out.println("- Xuat du lieu ra man hinh");
        System.out.println("- Xuat du lieu ra file");
    }
}
