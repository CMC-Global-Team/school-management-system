package Screen.ClassRoom;

import Models.Classroom;
import Screen.AbstractScreen;
import java.util.List;

public class DeleteClassRoomScreen extends AbstractScreen {

    @Override
    public void display() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║          XÓA LỚP HỌC                  ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    @Override
    public void handleInput() {
        List<Classroom> classrooms = AddClassRoomScreen.getClassrooms();
        
        if (classrooms.isEmpty()) {
            System.out.println("\nKhông có lớp học nào để xóa.");
            pause();
            return;
        }

        String classId = input("\nNhập Mã Lớp cần xóa: ");
        
        Classroom classroom = findClassById(classId);
        
        if (classroom == null) {
            System.out.println("Lỗi: Không tìm thấy lớp học!");
            pause();
            return;
        }

        System.out.println("\nĐã tìm thấy lớp học:");
        System.out.println(classroom);
        
        String confirm = input("\nBạn có chắc chắn muốn xóa lớp học này? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            classrooms.remove(classroom);
            System.out.println("\n✓ Xóa lớp học thành công!");
        } else {
            System.out.println("\nĐã hủy xóa.");
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