package Screen.Teacher;

import Screen.AbstractScreen;

public class Teachercreate extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           THÊM GIÁO VIÊN MỚI             │");
        System.out.println("└──────────────────────────────────────────┘");
    }


    @Override
    public void handleInput() {
        System.out.println("\n[Thong bao] Chuc nang Them Giao Vien dang duoc phat trien...");
        System.out.println("Cac thong tin se bao gom:");
        System.out.println("- Mã giáo viên");
        System.out.println("- Họ và tên");
        System.out.println("- Môn dạy");
        System.out.println("- Trình độ (Cử nhân/ Thạc sĩ/ Tiến sĩ)");
        System.out.println("- Kinh nghiệm (Năm)");
        System.out.println("- Lớp chủ nghiệm");
        System.out.println("- Số điện thoại");
        System.out.println("- Email");
        System.out.println("- Trạng thái (Đang dạy/ Nghỉ hưu/ Công tác)");

        pause();
    }
}

