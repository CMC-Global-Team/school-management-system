package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditSubjectScreen extends AbstractScreen {

    private static final String SUBJECT_FILE = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│          CHỈNH SỬA THÔNG TIN MÔN HỌC         │");
        System.out.println("└──────────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        List<String> subjectLines = new ArrayList<>();

        try {
            if (FileUtil.fileExists(SUBJECT_FILE)) {
                subjectLines = FileUtil.readLines(SUBJECT_FILE);
            } else {
                System.out.println("Không tìm thấy file môn học!");
                InputUtil.pressEnterToContinue();
                return;
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
            return;
        }

        if (subjectLines.isEmpty()) {
            System.out.println("Danh sách môn học trống!");
            InputUtil.pressEnterToContinue();
            return;
        }

        // Nhập mã môn học để chỉnh sửa
        String subjectId = InputUtil.getNonEmptyString("Nhập mã môn học cần chỉnh sửa: ");
        boolean found = false;

        for (int i = 0; i < subjectLines.size(); i++) {
            Subject s = Subject.fromString(subjectLines.get(i));
            if (s != null && s.getSubjectID().equalsIgnoreCase(subjectId)) {
                found = true;

                System.out.println("\n🔎 Thông tin hiện tại:");
                System.out.println("Tên môn học: " + s.getSubjectName());
                System.out.println("Số tiết: " + s.getLessonCount());
                System.out.println("Hệ số: " + s.getConfficient());
                System.out.println("Mô tả: " + s.getDescription());

                System.out.println("\n--- Nhập thông tin mới (bỏ trống nếu không thay đổi) ---");

                // Cho phép người dùng cập nhật 3 trường: số tiết, hệ số, mô tả
                String lessonsInput = InputUtil.getString("Số tiết mới: ");
                if (!lessonsInput.isEmpty()) {
                    try {
                        s.setLessonCount(Integer.parseInt(lessonsInput));
                    } catch (NumberFormatException e) {
                        System.out.println("Dữ liệu không hợp lệ, giữ nguyên giá trị cũ.");
                    }
                }

                String coeffInput = InputUtil.getString("Hệ số mới: ");
                if (!coeffInput.isEmpty()) {
                    try {
                        s.setConfficient(Double.parseDouble(coeffInput));
                    } catch (NumberFormatException e) {
                        System.out.println("Dữ liệu không hợp lệ, giữ nguyên giá trị cũ.");
                    }
                }

                String descInput = InputUtil.getString("Mô tả mới: ");
                if (!descInput.isEmpty()) {
                    s.setDescription(descInput);
                }

                // Cập nhật dòng trong danh sách
                subjectLines.set(i, s.toString());
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy mã môn học này!");
        } else {
            try {
                FileUtil.writeLines(SUBJECT_FILE, subjectLines);
                System.out.println("Đã cập nhật thông tin môn học thành công!");
            } catch (IOException e) {
                System.out.println("Lỗi khi lưu file: " + e.getMessage());
            }
        }

        InputUtil.pressEnterToContinue();
    }
}
