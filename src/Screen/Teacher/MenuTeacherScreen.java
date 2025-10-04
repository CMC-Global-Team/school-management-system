package Screen.Teacher;

import Screen.AbstractScreen;

public class TeacherMenu extends AbstractScreen {
    private final Teachercreate createScreen;
    private final Teacherupdate updateScreen;
    private final Teacherdelete deleteScreen;

    public TeacherMenu() {
        super();
        this.createScreen = new Teachercreate();
        this.updateScreen = new Teacherupdate();
        this.deleteScreen = new Teacherdelete();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│       HỆ THỐNG QUẢN LÝ GIÁO VIÊN         │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. Thêm Giáo Viên Mới                   │");
        System.out.println("│  2. Cập Nhật Giáo Viên                   │");
        System.out.println("│  3. Xóa Giáo Viên                        │");
        System.out.println("│  4. Tìm Kiếm Giáo Viên                   │");
        System.out.println("│  5. Danh Sách Tất Cả Giáo Viên           │");
        System.out.println("│  0. Quay Lại Menu Chính                  │");
        System.out.println("└──────────────────────────────────────────┘");
    }


    @Override
    public void handleInput() {
        boolean running = true;
        while (running) {
            clearScreen();
            display();
            int choice = inputInt("Nhap lua chon cua ban: ");

            switch (choice) {
                case 1:
                    createScreen.display();
                    createScreen.handleInput();
                    break;
                case 2:
                    updateScreen.display();
                    updateScreen.handleInput();
                    break;
                case 3:
                    deleteScreen.display();
                    deleteScreen.handleInput();
                    break;
                case 4:
                    System.out.println("\nChuc nang Tim Kiem Giao Vien dang duoc phat trien...");
                    pause();
                    break;
                case 5:
                    System.out.println("\nChuc nang Danh Sach Giao Vien dang duoc phat trien...");
                    pause();
                    break;
                case 0:
                    System.out.println("\nDang quay lai menu chinh...");
                    running = false;
                    break;
                default:
                    System.out.println("\nLua chon khong hop le. Vui long thu lai.");
                    pause();
            }
        }
    }
}

