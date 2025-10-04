import Screen.DashBoard;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        // Thiết lập UTF-8 cho console
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Khong the thiet lap UTF-8");
        }
        
        // Khởi tạo và chạy DashBoard - Menu chính của hệ thống
        DashBoard dashboard = new DashBoard();
        dashboard.handleInput(); // handleInput() sẽ tự động gọi display() bên trong
    }
}