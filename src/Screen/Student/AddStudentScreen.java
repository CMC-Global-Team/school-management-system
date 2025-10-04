package Screen.Student;

import Screen.AbstractScreen;

public class AddStudentScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("===========================================");
        System.out.println("          THEM HOC SINH MOI");
        System.out.println("===========================================");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Them Hoc Sinh dang duoc phat trien...");
        System.out.println("Cac thong tin se bao gom:");
        System.out.println("- Ma hoc sinh");
        System.out.println("- Ho va ten");
        System.out.println("- Ngay sinh");
        System.out.println("- Dia chi");
        System.out.println("- So dien thoai");
        System.out.println("- Lop hoc");
        pause();
    }
}

