package maktab.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher extends User {
    @ManyToMany
    @JoinTable(
            name = "Teacher_Course",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    public Teacher() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
