package Screen.Student;

import Screen.AbstractScreen;

public class ListStudentScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│       DANH SÁCH TẤT CẢ HỌC SINH          │");
        System.out.println("└──────────────────────────────────────────┘");
    }


    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Danh Sach Hoc Sinh dang duoc phat trien...");
        pause();
    }
}

