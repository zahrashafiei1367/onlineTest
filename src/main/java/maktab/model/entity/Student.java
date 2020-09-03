package maktab.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
//@NamedQuery(name = "Student.selectAll", query = "from Student")
//@NamedNativeQuery(name = "Student.selectAllBySQL", query = "select * from student", resultClass = Student.class)

public class Student extends User {
    @ManyToMany
    @JoinTable(
            name = "Student_Course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Student() {
    }



    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
