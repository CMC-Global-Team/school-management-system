package Screen.Tuition;

import Models.Tuition;
import Screen.AbstractScreen;
import Services.TuitionService;
import Utils.InputUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * ExportTuitionListScreen - Màn hình xuất danh sách học phí
 * Sử dụng TuitionService để quản lý dữ liệu thay vì trực tiếp đọc/ghi file
 */
public class ExportTuitionListScreen extends AbstractScreen {
    private final TuitionService tuitionService;

    public ExportTuitionListScreen() {
        this.tuitionService = TuitionService.getInstance();
    }

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│        Xuất Danh Sách Học Phí            │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\n[Thông báo] Xuất danh sách học phí...");

        String outputFile = "data/tuition_export.txt";
        if (tuitionService.exportTuitionsToFile(outputFile)) {
            System.out.println("[Thông báo] Đã xuất dữ liệu ra file: " + outputFile);
        } else {
            System.out.println("[Lỗi] Xuất dữ liệu thất bại!");
        }

        InputUtil.pressEnterToContinue();
    }
}