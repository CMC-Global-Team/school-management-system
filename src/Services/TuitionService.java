package Services;

import Models.Tuition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;


/**
 * TuitionService - Service layer cho quản lý học phí
 * Sử dụng Singleton pattern để đảm bảo instance duy nhất
 */
public class TuitionService {
    private static TuitionService instance;
    private final TuitionRepository repository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final StudentRepository studentRepository;

    private TuitionService() {
        this.repository = new TuitionRepository();

        this.studentRepository = new StudentRepository();
    }

    /**
     * Lấy instance duy nhất của TuitionService (Singleton)
     */
    public static TuitionService getInstance() {
        if (instance == null) {
            instance = new TuitionService();
        }
        return instance;
    }

    /**
     * Thêm học phí mới
     */
    public boolean addTuition(String tuitionId, String studentId, int semester, String schoolYear,
                              double amount, LocalDate paymentDate, String method, String status, String note) {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        //  1. Kiểm tra mã học phí (định dạng TF + 4 số)
        while (tuitionId == null || !tuitionId.matches("^TF\\d{4}$") || repository.exists(tuitionId)) {
            if (tuitionId == null || tuitionId.trim().isEmpty()) {
                System.out.print(" Mã học phí không được để trống! → Nhập lại (định dạng TFxxxx): ");
            } else if (!tuitionId.matches("^TF\\d{4}$")) {
                System.out.print(" Mã học phí sai định dạng! (VD: TF0001) → Nhập lại: ");
            } else {
                System.out.print(" Mã học phí '" + tuitionId + "' đã tồn tại! → Nhập mã khác: ");
            }
            tuitionId = sc.nextLine().trim();
        }

        //  2. Kiểm tra mã học sinh
        while (studentId == null || studentId.trim().isEmpty() || !studentRepository.exists(studentId)) {
            if (studentId == null || studentId.trim().isEmpty()) {
                System.out.print(" Mã học sinh không được để trống! → Nhập lại: ");
            } else {
                System.out.print(" Không tìm thấy học sinh có mã '" + studentId + "'! → Nhập lại: ");
            }
            studentId = sc.nextLine().trim();
        }

        //  3. Kiểm tra học sinh đã có học phí chưa
        List<Tuition> existingTuitions = repository.findAll();
        String finalStudentId = studentId;
        boolean hasTuition = existingTuitions.stream()
                .anyMatch(t -> t.getStudentId().equalsIgnoreCase(finalStudentId));

        if (hasTuition) {
            System.out.println("  ️ Học sinh có mã '" + studentId + "' đã có học phí trong hệ thống!");
            return false;
        }

        //  4. Nhập năm học đúng định dạng YYYY-YYYY
        while (schoolYear == null || !schoolYear.matches("^\\d{4}-\\d{4}$")) {
            System.out.print(" Năm học sai định dạng! (VD: 2023-2024) → Nhập lại: ");
            schoolYear = sc.nextLine().trim();
        }

        //  5. Số tiền phải là số dương, và tự cộng thêm 3 số 0
        amount = -1;
        DecimalFormat df = new DecimalFormat("#,###");

        while (amount <= 0) {
            System.out.print("Nhập số tiền (đơn vị: nghìn đồng, vd: 5): ");
            try {
                amount = Double.parseDouble(sc.nextLine().trim());
                if (amount <= 0) {
                    System.out.println(" Số tiền phải lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Vui lòng nhập số hợp lệ!");
                amount = -1;
            }
        }

// Nhân thêm 1000 để ra số thực tế
        amount *= 1000;

//  Hiển thị ra với dấu chấm phân cách hàng nghìn
        System.out.println("→ Số tiền thực thu: " + df.format(amount));

        //  6. Kiểm tra ngày thu không được lớn hơn hôm nay
        while (paymentDate == null || paymentDate.isAfter(LocalDate.now())) {
            try {
                System.out.print("Nhập ngày thu (dd-MM-yyyy): ");
                String input = sc.nextLine().trim();
                paymentDate = LocalDate.parse(input, DATE_FORMATTER);

                if (paymentDate.isAfter(LocalDate.now())) {
                    System.out.println(" Ngày thu không được vượt quá hôm nay (" + LocalDate.now().format(DATE_FORMATTER) + ")");
                    paymentDate = null; // reset để nhập lại
                }
            } catch (DateTimeParseException e) {
                System.out.println(" Định dạng ngày không hợp lệ, phải là dd-MM-yyyy. Nhập lại!");
                paymentDate = null;
            }
        }

        //  7. Trạng thái chỉ được nhập "ĐÃ ĐÓNG" hoặc "CHƯA ĐÓNG"
        while (!status.equalsIgnoreCase("ĐÃ ĐÓNG") && !status.equalsIgnoreCase("CHƯA ĐÓNG")) {
            System.out.print(" Trạng thái chỉ được là 'ĐÃ ĐÓNG' hoặc 'CHƯA ĐÓNG' → Nhập lại: ");
            status = sc.nextLine().trim().toUpperCase();
        }

//  8. Phương thức thanh toán chỉ nhập khi đã đóng
        if (status.equalsIgnoreCase("ĐÃ ĐÓNG")) {
            while (!method.equalsIgnoreCase("TIỀN MẶT") && !method.equalsIgnoreCase("CHUYỂN KHOẢN")) {
                System.out.print(" Phương thức chỉ được là 'TIỀN MẶT' hoặc 'CHUYỂN KHOẢN' → Nhập lại: ");
                method = sc.nextLine().trim().toUpperCase();
            }
        } else {
            method = ""; // nếu chưa đóng thì bỏ trống phương thức thanh toán
        }


        //  9. Lưu học phí
        Tuition tuition = new Tuition(tuitionId, studentId, semester, schoolYear, amount, paymentDate, method, status, note);
        if (repository.add(tuition)) {
            System.out.println(" Thêm học phí thành công cho học sinh " + studentId);
            return true;
        } else {
            System.out.println(" Lỗi: Không thể thêm học phí!");
            return false;
        }
    }




    /**
     * Cập nhật  chỉnh sửa học phí
     */
    public boolean updateTuition(Tuition tuition) {
        if (tuition == null) {
            System.out.println("Lỗi: Tuition không được null!");
            return false;
        }

        if (!repository.exists(tuition.getTuitionId())) {
            System.out.println("Lỗi: Không tìm thấy học phí với mã '" + tuition.getTuitionId() + "'!");
            return false;
        }

        if (repository.update(tuition)) {
            System.out.println("✓ Cập nhật học phí thành công!");
            return true;
        } else {
            System.out.println("Lỗi: Không thể cập nhật học phí!");
            return false;
        }
    }

    /**
     * Xóa học phí theo ID
     */
    public boolean deleteTuition(String tuitionId) {
        if (tuitionId == null || tuitionId.trim().isEmpty()) {
            System.out.println("Lỗi: Mã học phí không được để trống!");
            return false;
        }

        if (!repository.exists(tuitionId)) {
            System.out.println("Lỗi: Không tìm thấy học phí với mã '" + tuitionId + "'!");
            return false;
        }

        if (repository.delete(tuitionId)) {
            System.out.println(" Xóa học phí thành công!");
            return true;
        } else {
            System.out.println("Lỗi: Không thể xóa học phí!");
            return false;
        }
    }

    /**
     * Tìm học phí theo ID
     */
    public Optional<Tuition> findById(String tuitionId) {
        return repository.findById(tuitionId);
    }

    /**
     * Lấy tất cả học phí
     */
    public List<Tuition> getAllTuitions() {
        return repository.findAll();
    }

    /**
     * Tìm kiếm học phí theo từ khóa
     */
    public List<Tuition> searchTuitions(String keyword) {
        return repository.search(keyword);
    }

    /**
     * Kiểm tra mã học phí đã tồn tại chưa
     */
    public boolean isTuitionIdExists(String tuitionId) {
        return repository.exists(tuitionId);
    }

    /**
     * Đếm tổng số học phí
     */
    public int getTotalTuitions() {
        return repository.count();
    }

//    /**
//     * Hiển thị danh sách tất cả học phí
//     */
//    public void displayAllTuitions() {
//        List<Tuition> tuitions = getAllTuitions();
//
//        if (tuitions.isEmpty()) {
//            System.out.println("\nKhông có học phí nào trong hệ thống.");
//            return;
//        }
//
//        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
//        System.out.println("│                                         DANH SÁCH HỌC PHÍ                                                     │");
//        System.out.println("├───────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
//        System.out.printf("│ %-12s %-12s %-8s %-10s %-10s %-12s %-12s %-10s %-20s │%n",
//                "Mã HP", "Mã SV", "Học kỳ", "Năm học", "Số tiền", "Ngày TT", "Phương thức", "Trạng thái", "Ghi chú");
//        System.out.println("├───────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
//        for (Tuition t : tuitions) {
//            System.out.printf("│ %-12s %-12s %-8d %-10s %-10.2f %-12s %-12s %-10s %-20s │%n",
//                    truncate(t.getTuitionId(), 12),
//                    truncate(t.getStudentId(), 12),
//                    t.getSemester(),
//                    truncate(t.getSchoolYear(), 10),
//                    t.getAmount(),
//                    truncate(t.getPaymentDate() != null ? t.getPaymentDate().toString() : "", 12),
//                    truncate(t.getMethod(), 12),
//                    truncate(t.getStatus(), 10),
//                    truncate(t.getNote(), 20));
//        }
//        System.out.println("└───────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
//        System.out.println("Tổng số học phí: " + tuitions.size());
//    }

    /**
     * Hiển thị kết quả tìm kiếm học phí
     */
    public void displaySearchResults(String keyword) {
        List<Tuition> results = searchTuitions(keyword);

        if (results.isEmpty()) {
            System.out.println("\nKhông tìm thấy học phí nào phù hợp với từ khóa: '" + keyword + "'");
            return;
        }
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                     KẾT QUẢ TÌM KIẾM HỌC PHÍ                                                  │");
        System.out.println("├───────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ %-12s %-12s %-8s %-10s %-10s %-12s %-12s %-10s %-20s │%n",
                "Mã HP", "Mã SV", "Học kỳ", "Năm học", "Số tiền", "Ngày TT", "Phương thức", "Trạng thái", "Ghi chú");
        System.out.println("├───────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");

        for (Tuition t : results) {
            System.out.printf("│ %-12s %-12s %-8d %-10s %-10.2f %-12s %-12s %-10s %-20s │%n",
                    truncate(t.getTuitionId(), 12),
                    truncate(t.getStudentId(), 12),
                    t.getSemester(),
                    truncate(t.getSchoolYear(), 10),
                    t.getAmount(),
                    truncate(t.getPaymentDate() != null ? t.getPaymentDate().toString() : "", 12),
                    truncate(t.getMethod(), 12),
                    truncate(t.getStatus(), 10),
                    truncate(t.getNote(), 20)
            );
        }

        System.out.println("└───────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.println("Tìm thấy: " + results.size() + " học phí");
    }

    // Xuất danh sách học phí ra file
    public boolean exportTuitionsToFile(String filePath) {
        List<Tuition> tuitions = getAllTuitions();
        if (tuitions.isEmpty()) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Tuition t : tuitions) {
                bw.write(t.toFileString()); // phương thức này đã có trong model
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("[Lỗi] Không ghi được file: " + e.getMessage());
            return false;
        }
    }
    public static class TuitionReport {
        public double totalPaid;
        public double totalUnpaid;
        public double totalDiscount;
        public int countPaid;
        public int countUnpaid;
        public double expectedRevenue;
        public double actualRevenue;
    }

    /**
     * Tạo báo cáo tài chính theo năm học (nếu filterYear=null hoặc "" thì lấy tất cả)
     */
    public TuitionReport generateFinancialReport(String filterYear) {
        List<Tuition> tuitions = getAllTuitions();
        TuitionReport report = new TuitionReport();

        for (Tuition t : tuitions) {
            if (t == null) continue;
            if (filterYear != null && !filterYear.isEmpty() && !t.getSchoolYear().equalsIgnoreCase(filterYear)) {
                continue;
            }

            //  Kiểm tra phần trăm miễn giảm trong ghi chú
            double discountPercent = 0;
            if (t.getNote() != null && t.getNote().toLowerCase().contains("miễn giảm")) {
                try {
                    for (String part : t.getNote().split(" ")) {
                        if (part.endsWith("%")) {
                            discountPercent = Double.parseDouble(part.replace("%", ""));
                            break;
                        }
                    }
                } catch (Exception ignored) {}
            }

            double discountAmount = t.getAmount() * (discountPercent / 100);
            String status = t.getStatus().trim().toLowerCase();

            if (status.equals("đã đóng") || status.equals("đã thu")) {
                report.totalPaid += (t.getAmount() - discountAmount);
                report.totalDiscount += discountAmount;
                report.countPaid++;
            } else if (status.equals("chưa đóng") || status.equals("chưa thu")) {
                report.totalUnpaid += t.getAmount();
                report.countUnpaid++;
            }
        }

        report.expectedRevenue = report.totalPaid + report.totalUnpaid;
        report.actualRevenue = report.totalPaid;

        return report;
    }



    private String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }
}