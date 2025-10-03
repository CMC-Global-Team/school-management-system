package Screen.ClassRoom;

import Models.Classroom;
import Screen.AbstractScreen;
import java.util.List;
import java.util.stream.Collectors;

public class SearchClassRoomScreen extends AbstractScreen {

    @Override
    public void display() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         TÌM KIẾM LỚP HỌC              ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public void handleInput() {
        System.out.println("\nTùy chọn Tìm kiếm:");
        System.out.println("1. Tìm kiếm theo Mã Lớp");
        System.out.println("2. Tìm kiếm theo Tên Lớp");
        System.out.println("3. Tìm kiếm theo Năm Học");
        System.out.println("4. Tìm kiếm theo Tên Giáo viên");
        
        int choice = inputInt("\nNhập lựa chọn của bạn: ");
        
        List<Classroom> results = null;
        String keyword;
        
        switch (choice) {
            case 1:
                keyword = input("Nhập Mã Lớp: ");
                results = searchByClassId(keyword);
                break;
            case 2:
                keyword = input("Nhập Tên Lớp: ");
                results = searchByClassName(keyword);
                break;
            case 3:
                keyword = input("Nhập Năm Học: ");
                results = searchBySchoolYear(keyword);
                break;
            case 4:
                keyword = input("Nhập Tên Giáo viên: ");
                results = searchByTeacherName(keyword);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                pause();
                return;
        }
        
        displayResults(results);
        pause();
    }

    private List<Classroom> searchByClassId(String classId) {
        return AddClassRoomScreen.getClassrooms().stream()
                .filter(c -> c.getClassId().toLowerCase().contains(classId.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Classroom> searchByClassName(String className) {
        return AddClassRoomScreen.getClassrooms().stream()
                .filter(c -> c.getClassName().toLowerCase().contains(className.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Classroom> searchBySchoolYear(String schoolYear) {
        return AddClassRoomScreen.getClassrooms().stream()
                .filter(c -> c.getSchoolYear().toLowerCase().contains(schoolYear.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Classroom> searchByTeacherName(String teacherName) {
        return AddClassRoomScreen.getClassrooms().stream()
                .filter(c -> c.getHomeroomTeacher() != null && 
                        c.getHomeroomTeacher().getName().toLowerCase().contains(teacherName.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void displayResults(List<Classroom> results) {
        System.out.println("\n" + "=".repeat(80));
        if (results == null || results.isEmpty()) {
            System.out.println("Không tìm thấy lớp học nào!");
        } else {
            System.out.println("Tìm thấy " + results.size() + " lớp học:");
            System.out.println("=".repeat(80));
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
        System.out.println("=".repeat(80));
    }
}