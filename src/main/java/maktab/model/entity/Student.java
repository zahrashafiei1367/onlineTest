package maktab.model.entity;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Entity
//@NamedQuery(name = "Student.selectAll", query = "from Student")
//@NamedNativeQuery(name = "Student.selectAllBySQL", query = "select * from student", resultClass = Student.class)

public class Student extends User {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_Course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "student")
    private List<Result> results;
    @Transient
    private Boolean hasCourse;
    public Student() {
        setAuthority("student");
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

    public Boolean getHasCourse() {
        return hasCourse;
    }

    public void setHasCourse(Boolean hasCourse) {
        this.hasCourse = hasCourse;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", hasCourse=" + hasCourse +
                super.getName() +"} ";
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
