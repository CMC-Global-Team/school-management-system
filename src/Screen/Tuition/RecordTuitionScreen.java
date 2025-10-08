package Screen.Tuition;

import Screen.AbstractScreen;
import Services.TuitionService;
import Utils.InputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecordTuitionScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│       Ghi nhận thanh toán học phí        │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        TuitionService service = TuitionService.getInstance();

        String tuitionId = InputUtil.getNonEmptyString("Nhập mã học phí: ");
        String studentId = InputUtil.getNonEmptyString("Mã học sinh: ");
        int semester = InputUtil.getInt("Học kỳ: ");
        String schoolYear = InputUtil.getNonEmptyString("Năm học: ");
        double amount = InputUtil.getDouble("Số tiền thu: ");

        // 🗓 Nhập ngày theo định dạng dd-MM-yyyy
        LocalDate paymentDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                String dateStr = InputUtil.getNonEmptyString("Ngày thu (dd/MM/yyyy): ");
                paymentDate = LocalDate.parse(dateStr, formatter);
                break;
            } catch (Exception e) {
                System.out.println(" Định dạng ngày không hợp lệ! Vui lòng nhập lại (vd: 07/10/2025).");
            }
        }

        String status = InputUtil.getNonEmptyString("Trạng thái: ");
        String method = InputUtil.getNonEmptyString("Phương thức thanh toán: ");
        String note = InputUtil.getString("Ghi chú (nếu có): ");

        boolean success = service.addTuition(
                tuitionId,
                studentId,
                semester,
                schoolYear,
                amount,
                paymentDate,
                method,
                status,
                note
        );

        if (success) {
            System.out.println("\n Đã ghi nhận thanh toán học phí thành công!");
            System.out.println("→ Mã biên lai: " + tuitionId);
            System.out.println("→ Ngày thu: " + paymentDate.format(formatter));
        }

        InputUtil.pressEnterToContinue();
    }
}
