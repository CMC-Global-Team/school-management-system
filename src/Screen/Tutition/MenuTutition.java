package Tutition;

import Screen.AbstractScreen;
import Screen.Grade.DeleteGradeScreen;
import Screen.Grade.EditGradeScreen;
import Screen.Grade.ExportScreen;
import Screen.Grade.SearchForStudentGradesScreen;

public class MenuTutition extends AbstractScreen {
    private final RecordTutitionScreen recordTutitionScreen;
    private final EditGradeScreen editGradeScreen;
    private final DeleteGradeScreen deleteGradeScreen;
    private final SearchForStudentGradesScreen searchForStudentGradesScreen;
    private final TuitionDiscountScreen tuitionDiscountScreen;
    private final FinancialReportScreen financialReportScreen;
    private final ExportTutionListScreen exportTutionListScreen;
   public MenuTutition() {
       super();
        this.recordTutitionScreen = new RecordTutitionScreen();
        this.editGradeScreen = new EditGradeScreen();
        this.deleteGradeScreen = new DeleteGradeScreen();
        this.searchForStudentGradesScreen = new SearchForStudentGradesScreen();
        this.tuitionDiscountScreen = new TuitionDiscountScreen();
        this.financialReportScreen = new FinancialReportScreen();
        this.exportTutionListScreen = new ExportTutionListScreen();

   }
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│        HỆ THỐNG QUẢN LÝ HỌC SINH         │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. Ghi nhận thanh toán học phí          │");
        System.out.println("│  2. Sửa thông tin học phí                │");
        System.out.println("│  3. Xóa dữ liệu học phí                  │");
        System.out.println("│  4. Tra cứu học phí                      │");
        System.out.println("│  5. Miễn giảm học phí                    │");
        System.out.println("│  6. Báo cáo tài chính                    │");
        System.out.println("│  7. Xuất danh sách học phí               │");
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
                  recordTutitionScreen.display();
                  recordTutitionScreen.handleInput();
                    break;
                case 2:
                    editGradeScreen.display();
                    editGradeScreen.handleInput();
                    break;
                case 3:
                    deleteGradeScreen.display();
                    deleteGradeScreen.handleInput();
                    break;
                case 4:
                    searchForStudentGradesScreen.display();
                    searchForStudentGradesScreen.handleInput();
                    break;
                case 5:
                    tuitionDiscountScreen.display();
                    tuitionDiscountScreen.handleInput();
                    break;
                case 6:
                   financialReportScreen.display();
                   financialReportScreen.handleInput();
                    break;
                case 7:
                    exportTutionListScreen.display();
                    exportTutionListScreen.handleInput();
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
