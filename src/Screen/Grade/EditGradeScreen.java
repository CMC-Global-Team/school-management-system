package Screen.Grade;

import Models.Grade;
import Screen.AbstractScreen;
import Utils.FileUtil;
import Utils.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditGradeScreen extends AbstractScreen {
    private boolean updated;
    @Override
    public void display() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           Sửa Điểm Cho Học Sinh          │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        List<String> gradeLines = new ArrayList<>();
        List<String> subjectLines = new ArrayList<>();
        List<String> studentLines = new ArrayList<>();
        List<String> updateLines = new ArrayList<>();
        List<String> updatedGrade = new ArrayList<>();

        try {
            if (FileUtil.fileExists("data/students.txt")) {
                studentLines = FileUtil.readLines("src/Data/students.txt");
            } else {
                System.out.println("Không tìm thấy file học sinh!");
                pause();
                return;
            }
            if (FileUtil.fileExists("data/subjects.txt")){
                subjectLines = FileUtil.readLines("src/Data/subjects.txt");
            } else {
                System.out.println("Không tìm thấy file môn học!");
                pause();
                return;
            }
            if (FileUtil.fileExists("data/grades.txt")) {
                gradeLines = FileUtil.readLines("data/grades.txt");
            } else {
                System.out.println("Không tìm thấy file điểm số!");
                pause();
                return;
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file môn học/điểm số/học sinh: " + e.getMessage());
            pause();
            return;
        }

        if (gradeLines.isEmpty()) {
            System.out.println("Danh sách điểm trống!");
            pause();
            return;
        }
        String gradeID = InputUtil.getString("Nhập mã điểm cần chỉnh sửa(Enter để quay lại): ");
        if (gradeID.isEmpty()) {
            return;
        }
        if (!EnterGradeScreen.isExistGradeID(gradeID,  gradeLines)) {
            System.out.println("Không tìm thấy mã điểm " + gradeID + "!");
            pause();
            return;
        }

        Grade grade = EnterGradeScreen.getGradeByID(gradeID, gradeLines);

        System.out.println("\nThông tin hiện tại: ");
        if (grade != null) {
            List<String> info = new ArrayList<>();
            info.add(grade.toString());
            SearchForStudentGradesScreen.displayResults(info);
        }
        System.out.println("\nBạn muốn cập nhật thông tin gì?");
        System.out.println("1. Mã học sinh");
        System.out.println("2. Mã môn");
        System.out.println("3. Loại điểm");
        System.out.println("4. Điểm");
        System.out.println("5. Kỳ học");
        System.out.println("6. Năm học");
        System.out.println("7. Ghi chú");
        System.out.println("8. Cập nhật Tất cả");
        System.out.println("0. Hủy");

        int choice = inputInt("\nNhập lựa chọn của bạn(0-8): ");

        switch (choice) {
            case 1:
                updateStudentID(grade, studentLines);
                break;
            case 2:
                updateSubjectID(grade, subjectLines);
                break;
            case 3:
                updateGradeType(grade, gradeLines);
                break;
            case 4:
                updateScore(grade);
                break;
            case 5:
                updateSemester(grade);
                break;
            case 6:
                updateSchoolYear(grade);
                break;
            case 7:
                updateNote(grade);
                break;
            case 8:
                updateAll(grade, studentLines, subjectLines, gradeLines);
                break;
            case 0:
                System.out.println("Đã hủy cập nhật.");
                pause();
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                pause();
                return;
        }
        if(updated){
            updateInfo(gradeLines, updateLines, grade);
            System.out.println("\n✓ Cập nhật điểm thành công!");
            System.out.println("\nThông tin đã cập nhật: ");
            updatedGrade.add(grade.toString());
            SearchForStudentGradesScreen.displayResults(updatedGrade);
        }else {
            System.out.println("Không có thay đổi nào được thực hiện.");
        }
        pause();
    }

    private void updateStudentID(Grade grade,List<String> studentLines ) {
        String studentID = InputUtil.getNonEmptyString("Mã học sinh: ");
        if(EnterGradeScreen.isExistStudentID(studentID, studentLines)){
            grade.setStudentId(studentID);
            updated = true;
        } else{
            System.out.println("Mã học sinh "+ studentID + " không tồn tại!");
        }
    }

    private void updateSubjectID(Grade grade,List<String> subjectLines ) {
        String subjectID = InputUtil.getNonEmptyString("Mã môn học: ");
        if(EnterGradeScreen.isExistSubjectID(subjectID, subjectLines)){
            grade.setSubjectId(subjectID);
            updated = true;
        } else {
            System.out.println("Mã môn "+ subjectID + " không tồn tại!");
        }
    }

    private void updateGradeType(Grade grade, List<String> gradeLines) {
        String gradeType = InputUtil.getNonEmptyString("Loại điểm: ");
        if(!gradeType.isEmpty()){
            if(EnterGradeScreen.isExistGradeType(grade.getGradeType(), grade.getStudentId(), gradeLines)){
                grade.setGradeType(gradeType);
                updated = true;
            }
        }
    }

    private void updateScore(Grade grade) {
        double score = InputUtil.getInt("Điểm: ");
        grade.setScore(score);
        updated = true;
    }

    private void updateSemester(Grade grade) {
        int Semester = inputInt("Học kỳ: ");
        if (Semester != 0) {
            grade.setSemester(Semester);
            updated = true;
        }
    }

    private void updateSchoolYear(Grade grade) {
        String schoolYear = EnterGradeScreen.schoolYearInput();
        if(!schoolYear.isEmpty()){
            grade.setSchoolYear(schoolYear);
            updated = true;
        }
    }

    private void updateNote(Grade grade) {
        String note = InputUtil.getString("Ghi chú: ");
        if(!note.isEmpty()){
            grade.setNote(note);
            updated = true;
        }
    }

    private void updateAll(Grade grade, List<String> studentLines, List<String> subjectLines, List<String> gradeLines) {
        updateStudentID(grade, studentLines);
        updateSubjectID(grade, subjectLines);
        updateGradeType(grade, gradeLines);
        updateScore(grade);
        updateSemester(grade);
        updateSchoolYear(grade);
        updateNote(grade);
    }
    private void updateInfo(List<String> gradeLines, List<String> updateLines ,Grade grade) {
        for(String line : gradeLines){
            Grade g = Grade.fromString(line);
            if(g != null && g.getGradeId().equalsIgnoreCase(grade.getGradeId())){
                updateLines.add(grade.toString());
            }
            else {
                updateLines.add(line);
            }
        }
        try{
            FileUtil.writeLines("src/Data/grades.txt",updateLines);
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật: " + e.getMessage());
            pause();
        }
    }
}
