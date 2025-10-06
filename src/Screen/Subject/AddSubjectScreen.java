package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           THÊM MÔN HỌC MỚI               │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        List<String> subjectLines = new ArrayList<>();
        try {
            if (FileUtil.fileExists(FILE_PATH)) {
                subjectLines = FileUtil.readLines(FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }

        String id;
        while (true) {
            id = InputUtil.getNonEmptyString("Mã môn học: ");
            boolean exists = false;
            for (String line : subjectLines) {
                Subject s = Subject.fromString(line);
                if (s != null && s.getSubjectID().equalsIgnoreCase(id)) {
                    exists = true;
                    break;
                }
            }
            if (exists) System.out.println("Mã môn học đã tồn tại! Nhập lại.");
            else break;
        }

        String name = InputUtil.getNonEmptyString("Tên môn học: ");
        int lessons = InputUtil.getInt("Số tiết: ");
        double coeff = InputUtil.getDouble("Hệ số: ");
        String type = InputUtil.getNonEmptyString("Loại môn (Bắt buộc/Tự chọn): ");
        String desc = InputUtil.getString("Mô tả: ");
        String teacher = InputUtil.getNonEmptyString("Giáo viên phụ trách: ");
        String status = InputUtil.getNonEmptyString("Trạng thái (Hoạt động/Ngừng): ");

        Subject newSubject = new Subject(id, name, lessons, coeff, type, desc, teacher, status);
        subjectLines.add(newSubject.toString());

        try {
            FileUtil.writeLines(FILE_PATH, subjectLines);
            System.out.println("\nĐã thêm môn học thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
