package Screen.Student;

import Screen.AbstractScreen;

public class UpdateStudentScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("===========================================");
        System.out.println("          CAP NHAT HOC SINH");
        System.out.println("===========================================");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Cap Nhat Hoc Sinh dang duoc phat trien...");
        pause();
    }
}

