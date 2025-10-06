package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class AddSubjectScreen extends AbstractScreen {

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│             THÊM MÔN HỌC MỚI             │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {

        List<String> subjectLines = new ArrayList<>();
        try {
            if (FileUtil.fileExists("src/Data/subjects.txt")) {
                subjectLines = FileUtil.readLines("src/Data/subjects.txt");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file môn học: " + e.getMessage());
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

            if (exists) {
                System.out.println("Mã môn học đã tồn tại! Vui lòng dùng mã khác.");
            } else break;
        }

        String name = InputUtil.getNonEmptyString("Tên môn học: ");
        int lessonCount = InputUtil.getInt("Số tiết học");

        double coficient;
        try {
            coficient = Double.parseDouble(InputUtil.getNonEmptyString("Hệ số:"));
        } catch (NumberFormatException e) {
            System.out.println("Giá trị không hợp lệ!");
            coficient = Double.parseDouble(InputUtil.getNonEmptyString("Hệ số:"));
        }
        String type = InputUtil.getNonEmptyString("Loại môn: ");


        String description = InputUtil.getNonEmptyString("Mô tả: ");
        String status = InputUtil.getNonEmptyString("Trạng thái: ");
        String teacherInCharge = InputUtil.getString("Giáo viên phụ trách");

        Subject newSubject = new Subject(id, name, lessonCount, coficient, type, description, teacherInCharge , status);

        subjectLines.add(newSubject.toString());

        // Save file
        try {
            FileUtil.writeLines("src/Data/subjects.txt", subjectLines);
            System.out.println("\nĐã thêm môn học thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
