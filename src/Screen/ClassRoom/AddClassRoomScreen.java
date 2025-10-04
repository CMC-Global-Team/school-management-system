package Screen.ClassRoom;

import Models.Classroom;
import Models.Teacher;
import Screen.AbstractScreen;
import java.util.ArrayList;
import java.util.List;

public class AddClassRoomScreen extends AbstractScreen {
    private static final List<Classroom> classrooms = new ArrayList<>();

    @Override
    public void display() {
        clearScreen();
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│            THÊM LỚP HỌC MỚI              │");
        System.out.println("└──────────────────────────────────────────┘");
    }


    @Override
    public void handleInput() {
        System.out.println("\nNhập thông tin lớp học:");
        
        String classId = input("Mã Lớp: ");
        
        // Check if class ID already exists
        if (findClassById(classId) != null) {
            System.out.println("Lỗi: Mã lớp đã tồn tại!");
            pause();
            return;
        }

        String className = input("Tên Lớp: ");
        String schoolYear = input("Năm Học (vd: 2024-2025): ");
        String course = input("Niên Khóa (vd: 2020-2024): ");
        
        System.out.println("\nThông tin Giáo viên Chủ nhiệm:");
        String teacherId = input("Mã Giáo viên: ");
        String teacherName = input("Tên Giáo viên: ");
        String teacherSubject = input("Môn Giảng dạy: ");
        String teacherDegree = input("Học vị: ");
        int teacherExperience = inputInt("Số năm kinh nghiệm: ");
        String teacherEmail = input("Email: ");
        String teacherPhone = input("Số điện thoại: ");
        String teacherHomeroom = input("Lớp Chủ nhiệm: ");
        
        Teacher teacher = new Teacher(teacherId, teacherName, "Active", 
                                      teacherSubject, teacherDegree, teacherExperience, 
                                      teacherEmail, teacherPhone, teacherHomeroom);
        
        Classroom classroom = new Classroom(classId, className, schoolYear, course, teacher);
        classrooms.add(classroom);
        
        System.out.println("\n✓ Thêm lớp học thành công!");
        System.out.println(classroom);
        pause();
    }

    public static List<Classroom> getClassrooms() {
        return classrooms;
    }

    private Classroom findClassById(String classId) {
        return classrooms.stream()
                .filter(c -> c.getClassId().equalsIgnoreCase(classId))
                .findFirst()
                .orElse(null);
    }
}