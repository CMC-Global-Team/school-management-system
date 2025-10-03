package Screen.ClassRoom;

import Models.Classroom;
import Screen.AbstractScreen;
import java.util.List;

public class ListClassRoomScreen extends AbstractScreen {

    @Override
    public void display() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      DANH SÁCH TẤT CẢ LỚP HỌC         ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public void handleInput() {
        List<Classroom> classrooms = AddClassRoomScreen.getClassrooms();
        
        System.out.println("\n" + "=".repeat(100));
        
        if (classrooms.isEmpty()) {
            System.out.println("Không có lớp học nào.");
        } else {
            System.out.println("Tổng số Lớp học: " + classrooms.size());
            System.out.println("=".repeat(100));
            
            System.out.printf("%-5s %-10s %-20s %-15s %-15s %-25s %-10s%n",
                    "STT", "Mã Lớp", "Tên Lớp", "Năm Học", "Niên Khóa", "GVCN", "Học Sinh");
            System.out.println("-".repeat(100));
            
            for (int i = 0; i < classrooms.size(); i++) {
                Classroom c = classrooms.get(i);
                String teacherName = c.getHomeroomTeacher() != null ? c.getHomeroomTeacher().getName() : "N/A";
                
                System.out.printf("%-5d %-10s %-20s %-15s %-15s %-25s %-10d%n",
                        (i + 1),
                        c.getClassId(),
                        c.getClassName(),
                        c.getSchoolYear(),
                        c.getCourse(),
                        teacherName,
                        c.getStudents().size());
            }
        }
        
        System.out.println("=".repeat(100));
        
        System.out.println("\nTùy chọn:");
        System.out.println("1. Xem thông tin chi tiết của một lớp học");
        System.out.println("0. Quay lại");
        
        int choice = inputInt("\nNhập lựa chọn của bạn: ");
        
        if (choice == 1 && !classrooms.isEmpty()) {
            viewDetails();
        }
    }

    private void viewDetails() {
        String classId = input("\nNhập Mã Lớp để xem chi tiết: ");
        Classroom classroom = findClassById(classId);
        
        if (classroom == null) {
            System.out.println("Không tìm thấy lớp học!");
        } else {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("CHI TIẾT LỚP HỌC");
            System.out.println("=".repeat(80));
            System.out.println("Mã Lớp: " + classroom.getClassId());
            System.out.println("Tên Lớp: " + classroom.getClassName());
            System.out.println("Năm Học: " + classroom.getSchoolYear());
            System.out.println("Niên Khóa: " + classroom.getCourse());
            
            if (classroom.getHomeroomTeacher() != null) {
                System.out.println("\nGiáo viên Chủ nhiệm:");
                System.out.println("  Họ tên: " + classroom.getHomeroomTeacher().getName());
                System.out.println("  Môn: " + classroom.getHomeroomTeacher().getTeacherSubject());
            }
            
            System.out.println("\nTổng số Học sinh: " + classroom.getStudents().size());
            System.out.println("Tổng số Môn học: " + classroom.getSubjects().size());
            System.out.println("=".repeat(80));
        }
        
        pause();
    }

    private Classroom findClassById(String classId) {
        return AddClassRoomScreen.getClassrooms().stream()
                .filter(c -> c.getClassId().equalsIgnoreCase(classId))
                .findFirst()
                .orElse(null);
    }
}