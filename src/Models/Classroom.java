package Models;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {
    private String classId;
    private String className;
    private String schoolYear;
    private String course; // niên khóa
    private Teacher homeroomTeacher; // giáo viên chủ nhiệm
    private List<Student> students;
    private List<Subject> subjects;

    public SchoolClass() {
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public SchoolClass(String classId, String className, String schoolYear,
                       String course, Teacher homeroomTeacher) {
        this.classId = classId;
        this.className = className;
        this.schoolYear = schoolYear;
        this.course = course;
        this.homeroomTeacher = homeroomTeacher;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    // Getter & Setter
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getSchoolYear() { return schoolYear; }
    public void setSchoolYear(String schoolYear) { this.schoolYear = schoolYear; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public Teacher getHomeroomTeacher() { return homeroomTeacher; }
    public void setHomeroomTeacher(Teacher homeroomTeacher) { this.homeroomTeacher = homeroomTeacher; }

    public List<Student> getStudents() { return students; }
    public List<Subject> getSubjects() { return subjects; }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String studentId) {
        students.removeIf(s -> s.getId().equals(studentId));
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public String toString() {
        return "Class: " + classId + " - " + className +
                ", Year: " + schoolYear +
                ", Course: " + course +
                ", Homeroom Teacher: " + (homeroomTeacher != null ? homeroomTeacher.getName() : "N/A") +
                ", Students: " + students.size() +
                ", Subjects: " + subjects.size();
    }
}
