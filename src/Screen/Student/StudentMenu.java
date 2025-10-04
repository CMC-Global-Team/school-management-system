package Screen.Student;

import Screen.AbstractScreen;

public class StudentMenu extends AbstractScreen {
    private final AddStudentScreen addScreen;
    private final UpdateStudentScreen updateScreen;
    private final DeleteStudentScreen deleteScreen;
    private final SearchStudentScreen searchScreen;
    private final ListStudentScreen listScreen;

    public StudentMenu() {
        super();
        this.addScreen = new AddStudentScreen();
        this.updateScreen = new UpdateStudentScreen();
        this.deleteScreen = new DeleteStudentScreen();
        this.searchScreen = new SearchStudentScreen();
        this.listScreen = new ListStudentScreen();
    }

    @Override
    public void display() {
        System.out.println("===========================================");
        System.out.println("       HE THONG QUAN LY HOC SINH");
        System.out.println("===========================================");
        System.out.println("  1. Them Hoc Sinh Moi");
        System.out.println("  2. Tim Kiem Hoc Sinh");
        System.out.println("  3. Cap Nhat Hoc Sinh");
        System.out.println("  4. Xoa Hoc Sinh");
        System.out.println("  5. Danh Sach Tat Ca Hoc Sinh");
        System.out.println("  0. Quay Lai Menu Chinh");
        System.out.println("===========================================");
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
                    addScreen.display();
                    addScreen.handleInput();
                    break;
                case 2:
                    searchScreen.display();
                    searchScreen.handleInput();
                    break;
                case 3:
                    updateScreen.display();
                    updateScreen.handleInput();
                    break;
                case 4:
                    deleteScreen.display();
                    deleteScreen.handleInput();
                    break;
                case 5:
                    listScreen.display();
                    listScreen.handleInput();
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

