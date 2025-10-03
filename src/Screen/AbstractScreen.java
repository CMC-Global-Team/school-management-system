package Screen;

import java.util.Scanner;

public abstract class AbstractScreen implements IScreen {
    protected Scanner scanner;

    public AbstractScreen() {
        this.scanner = new Scanner(System.in);
    }

    protected String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    protected int inputInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Định dạng số không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    protected void pause() {
        System.out.println("\nNhấn Enter để tiếp tục...");
        scanner.nextLine();
    }

    protected void clearScreen() {
        // Simple console clear simulation
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}