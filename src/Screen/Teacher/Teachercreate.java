package Screen.Teacher;

import Screen.AbstractScreen;

public class Teachercreate extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           THÊM GIÁO VIÊN MỚI             │");
        System.out.println("└──────────────────────────────────────────┘");
    }


    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Them Giao Vien dang duoc phat trien...");
        System.out.println("Cac thong tin se bao gom:");
        System.out.println("- Ma giao vien");
        System.out.println("- Ho va ten");
        System.out.println("- Ngay sinh");
        System.out.println("- Dia chi");
        System.out.println("- So dien thoai");
        System.out.println("- Mon day");
        pause();
    }
}

