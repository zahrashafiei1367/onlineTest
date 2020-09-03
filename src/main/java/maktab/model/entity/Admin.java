package maktab.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Admin extends User {
    @Pattern(regexp = "^[a-zA-Z]+$" , message="*school name should be filled.")
    private String schoolName;
    @OneToMany(mappedBy = "admin")
    private List<Student> students;
    @OneToMany(mappedBy = "admin")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "admin")
    private List<Course> courses;

    public Admin() {
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
