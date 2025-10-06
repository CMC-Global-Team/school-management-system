package Screen.Subject;

import Models.Subject;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateSubjectScreen extends AbstractScreen {

    private static final String FILE_PATH = "src/Data/subjects.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           CẬP NHẬT MÔN HỌC               │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        String id = InputUtil.getNonEmptyString("Nhập mã môn học cần cập nhật: ");

        try {
            List<String> lines = FileUtil.readLines(FILE_PATH);
            List<String> updated = new ArrayList<>();
            boolean found = false;

            for (String line : lines) {
                Subject s = Subject.fromString(line);
                if (s != null && s.getSubjectID().equalsIgnoreCase(id)) {
                    found = true;
                    System.out.println("Đang cập nhật môn: " + s.getSubjectName());
                    String newName = InputUtil.getString("Tên mới (Enter để giữ nguyên): ");
                    if (!newName.isEmpty()) s.setSubjectName(newName);

                    String newDesc = InputUtil.getString("Mô tả mới (Enter để giữ nguyên): ");
                    if (!newDesc.isEmpty()) s.setDescription(newDesc);

                    String newStatus = InputUtil.getString("Trạng thái mới (Enter để giữ nguyên): ");
                    if (!newStatus.isEmpty()) s.setStatus(newStatus);

                    updated.add(s.toString());
                } else {
                    updated.add(line);
                }
            }

            if (found) {
                FileUtil.writeLines(FILE_PATH, updated);
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy môn học!");
            }

        } catch (IOException e) {
            System.out.println("Lỗi cập nhật: " + e.getMessage());
        }

        InputUtil.pressEnterToContinue();
    }
}
