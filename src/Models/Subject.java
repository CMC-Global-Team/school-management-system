package Models;

public class Subject {
    private String subjectID;
    private String subjectName;
    private int lessonCount;
    private double confficient;
    private String subjectType;
    private String description;
    private String teacherInCharge;
    private String status;

    public Subject(){

    }

    public Subject(String subjectID, String subjectName, int lessonCount, double confficient, String subjectType, String description, String teacherInCharge, String status) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.lessonCount = lessonCount;
        this.confficient = confficient;
        this.subjectType = subjectType;
        this.description = description;
        this.teacherInCharge = teacherInCharge;
        this.status = status;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public double getConfficient() {
        return confficient;
    }

    public void setConfficient(double confficient) {
        this.confficient = confficient;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherInCharge() {
        return teacherInCharge;
    }

    public void setTeacherInCharge(String teacherInCharge) {
        this.teacherInCharge = teacherInCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return subjectID + "," + subjectName + "," + lessonCount + "," + confficient + "," + subjectType +
                "," + description + "," + teacherInCharge + "," + status;
    }

    public static Subject fromString(String line){
        String[] parts = line.split(",");
        if (parts.length != 8) return null;
        try{
            return new Subject(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    Double.parseDouble(parts[3]),
                    parts[4],
                    parts[5],
                    parts[6],
                    parts[7]
            );
        } catch (Exception e) {
            return null;
        }
    }
}
