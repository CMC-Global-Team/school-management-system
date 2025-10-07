package Screen.Subject;

import Utils.IdGenerator;
import Screen.AbstractScreen;
import Services.SubjectService;
import Models.Subject;
import Utils.InputUtil;

import java.util.List;

public class AddSubjectScreen extends AbstractScreen {
    private final SubjectService subjectService;

    public AddSubjectScreen() {
        this.subjectService = SubjectService.getInstance();
    }

    @Override
    public void display() {
        clearScreen();
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│           THÊM MÔN HỌC MỚI               │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    @Override
    public void handleInput() {
        System.out.println("\nNhập thông tin môn học:");


        List<Subject> subjects = subjectService.getAllSubjects(); // lấy toàn bộ môn học
        List<String> ids = subjects.stream()
                .map(Subject::getSubjectID)
                .toList();

        String id = IdGenerator.generateId(ids, "M", 2);
        System.out.println("Mã môn học gợi ý: " + id);
        String suggestedId = input("Mã Môn Học (nhấn Enter để dùng gợi ý): ").trim();
        if (suggestedId.isEmpty()) suggestedId = id;
        String name = input("Tên Môn Học: ");
        int lessonCount = InputUtil.getInt("Số tiết học: ");
        double confficient = InputUtil.getDouble("Hệ số: ");
        String type = input("Loại Môn (Bắt buộc/Tự chọn): ");
        String description = input("Mô tả: ");
        String teacher = input("Giáo viên phụ trách: ");
        String status = input("Trạng thái (Đang dạy/Ngừng): ");

        if (subjectService.addSubject(id, name, lessonCount, confficient, type, description, teacher, status)) {
            System.out.println("\nThông tin môn học đã thêm:");
            subjectService.findById(id).ifPresent(System.out::println);
        }

        pause();
    }
}
