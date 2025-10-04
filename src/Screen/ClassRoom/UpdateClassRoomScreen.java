package Screen.ClassRoom;

import Models.Classroom;
import Models.Teacher;
import Screen.AbstractScreen;

public class UpdateClassRoomScreen extends AbstractScreen {

    @Override
    public void display() {
        clearScreen();
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│            CẬP NHẬT LỚP HỌC              │");
        System.out.println("└──────────────────────────────────────────┘");
    }


    @Override
    public void handleInput() {
        if (AddClassRoomScreen.getClassrooms().isEmpty()) {
            System.out.println("\nKhông có lớp học nào để cập nhật.");
            pause();
            return;
        }

        String classId = input("\nNhập Mã Lớp cần cập nhật: ");
        
        Classroom classroom = findClassById(classId);
        
        if (classroom == null) {
            System.out.println("Lỗi: Không tìm thấy lớp học!");
            pause();
            return;
        }

        System.out.println("\nThông tin hiện tại:");
        System.out.println(classroom);
        
        System.out.println("\nBạn muốn cập nhật thông tin gì?");
        System.out.println("1. Tên Lớp");
        System.out.println("2. Năm Học");
        System.out.println("3. Niên Khóa");
        System.out.println("4. Giáo viên Chủ nhiệm");
        System.out.println("5. Cập nhật Tất cả");
        System.out.println("0. Hủy");
        
        int choice = inputInt("\nNhập lựa chọn của bạn: ");
        
        switch (choice) {
            case 1:
                updateClassName(classroom);
                break;
            case 2:
                updateSchoolYear(classroom);
                break;
            case 3:
                updateCourse(classroom);
                break;
            case 4:
                updateTeacher(classroom);
                break;
            case 5:
                updateAll(classroom);
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
        
        System.out.println("\n✓ Cập nhật lớp học thành công!");
        System.out.println("\nThông tin đã cập nhật:");
        System.out.println(classroom);
        pause();
    }

    private void updateClassName(Classroom classroom) {
        String newName = input("Nhập Tên Lớp mới (hiện tại: " + classroom.getClassName() + "): ");
        if (!newName.isEmpty()) {
            classroom.setClassName(newName);
        }
    }

    private void updateSchoolYear(Classroom classroom) {
        String newYear = input("Nhập Năm Học mới (hiện tại: " + classroom.getSchoolYear() + "): ");
        if (!newYear.isEmpty()) {
            classroom.setSchoolYear(newYear);
        }
    }

    private void updateCourse(Classroom classroom) {
        String newCourse = input("Nhập Niên Khóa mới (hiện tại: " + classroom.getCourse() + "): ");
        if (!newCourse.isEmpty()) {
            classroom.setCourse(newCourse);
        }
    }

    private void updateTeacher(Classroom classroom) {
        System.out.println("\nGiáo viên hiện tại: " + 
                (classroom.getHomeroomTeacher() != null ? classroom.getHomeroomTeacher().getName() : "N/A"));
        
        System.out.println("\nNhập thông tin giáo viên mới:");
        String teacherId = input("Mã Giáo viên: ");
        String teacherName = input("Tên Giáo viên: ");
        String teacherSubject = input("Môn Giảng dạy: ");
        String teacherDegree = input("Học vị: ");
        int teacherExperience = inputInt("Số năm kinh nghiệm: ");
        String teacherEmail = input("Email: ");
        String teacherPhone = input("Số điện thoại: ");
        String teacherHomeroom = input("Lớp Chủ nhiệm: ");
        
        Teacher newTeacher = new Teacher(teacherId, teacherName, "Active", 
                                         teacherSubject, teacherDegree, teacherExperience, 
                                         teacherEmail, teacherPhone, teacherHomeroom);
        classroom.setHomeroomTeacher(newTeacher);
    }

    private void updateAll(Classroom classroom) {
        updateClassName(classroom);
        updateSchoolYear(classroom);
        updateCourse(classroom);
        updateTeacher(classroom);
    }

    private Classroom findClassById(String classId) {
        return AddClassRoomScreen.getClassrooms().stream()
                .filter(c -> c.getClassId().equalsIgnoreCase(classId))
                .findFirst()
                .orElse(null);
    }
}