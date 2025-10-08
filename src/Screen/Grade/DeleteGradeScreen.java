package Screen.Grade;

import Models.Grade;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;
import java.util.ArrayList;
import java.util.List;

public class DeleteGradeScreen extends AbstractScreen {
    private static final String FILE_PATH = "src/Data/grades.txt";

    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           Xoá Điểm Học Sinh              │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        List<String> gradeLines = new ArrayList<>();
        List<String> remains = new ArrayList<>();
        try {
            if(FileUtil.fileExists("dta/grades.txt")) {
                gradeLines = FileUtil.readLines("data/grades.txt");
            }else {
                System.out.println("Không tìm thấy file học sinh!");
                pause();
                return;
            }
            boolean deleted = false;
            String gradeID = InputUtil.getString("Nhập mã điểm cần xoá(Enter để huỷ): ");
            if(gradeID.isEmpty()) {
                return;
            }
            if(EnterGradeScreen.isExistGradeID(gradeID, gradeLines)) {
                for (String line : gradeLines) {
                    Grade g = Grade.fromString(line);
                    if (g != null && g.getGradeId().equalsIgnoreCase(gradeID)) {
                        deleted = true;

                    } else {
                        remains.add(line);
                    }
                }
            }else {
                deleted = false;
                System.out.println("Không tìm thấy điểm có mã: " + gradeID);
            }
            if(deleted && InputUtil.getBoolean("Bạn có chắc muốn xoá điểm này? ")){
                FileUtil.writeLines(FILE_PATH, remains);
                System.out.println("Đã xoá điểm có mã:" + gradeID);
            }
            pause();
        }catch (Exception e){
            System.out.println("Lỗi khi đọc/ghi file điểm số: " + e.getMessage());
            pause();
        }
    }
    public static List<String> ignoreSubjectID(String subjectID, List<String> gradeLines) {
        List<String> remains = new ArrayList<>();
        if(!subjectID.isEmpty()) {
            for (String line : gradeLines) {
                Grade g = Grade.fromString(line);
                if (g != null && !g.getSubjectId().equalsIgnoreCase(subjectID)) {
                    remains.add(line);
                }
            }
        }
        return remains;
    }
}
