package Screen.Student;

import Screen.AbstractScreen;

public class SearchStudentScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("===========================================");
        System.out.println("          TIM KIEM HOC SINH");
        System.out.println("===========================================");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Tim Kiem Hoc Sinh dang duoc phat trien...");
        pause();
    }
}

