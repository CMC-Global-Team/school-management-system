package Screen.Teacher;

import Screen.AbstractScreen;

public class Teacherdelete extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("===========================================");
        System.out.println("            XOA GIAO VIEN");
        System.out.println("===========================================");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Xoa Giao Vien dang duoc phat trien...");
        pause();
    }
}

