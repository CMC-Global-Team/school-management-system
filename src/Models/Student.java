package Models;

import java.time.LocalDate;

public class Student extends Person {
    private LocalDate dateOfBirth;
    private String gender;
    private String className;
    private String course;
    private String parentPhone;
    private String address;

    public Student(){
    }

    public Student(String id,String name ,LocalDate dateOfBirth, String gender, String className, String course, String parentPhone, String address, String status){
        super(id, name, status);
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.className = className;
        this.course = course;
        this.parentPhone = parentPhone;
        this.address = address;
    }

    public LocalDate getDateOfBirth(){return dateOfBirth;}
    public void setDateOfBirth(LocalDate dateOfBirth){this.dateOfBirth = dateOfBirth;}

    public String getGender(){return gender;}
    public void setGender(String gender){this.gender = gender;}

    public String getClassName(){return className;}
    public void setClassName(String className){this.className = className;}

    public String getCourse(){return course;}
    public void setCourse(String course){this.course = course;}

    public String getParentPhone(){return parentPhone;}
    public void setParentPhone(String parentPhone){this.parentPhone = parentPhone;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    @Override
    public String toString(){
        return id + "," + name + "," + dateOfBirth + "," + gender + "," + className + "," +
                course + "," + parentPhone + "," + address;
    }

    public static  Student fromString(String line){
        String[] parts = line.split(",");
        if(parts.length != 9) return null;

        try{
            return new Student(
                    parts[0],
                    parts[1],
                    LocalDate.parse(parts[2]),
                    parts[3],
                    parts[4],
                    parts[5],
                    parts[6],
                    parts[7],
                    parts[8]
            );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
