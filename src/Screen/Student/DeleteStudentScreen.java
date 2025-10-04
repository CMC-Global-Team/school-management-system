package Screen.Student;

import Screen.AbstractScreen;

public class DeleteStudentScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│               XÓA HỌC SINH               │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Xoa Hoc Sinh dang duoc phat trien...");
        pause();
    }
}

