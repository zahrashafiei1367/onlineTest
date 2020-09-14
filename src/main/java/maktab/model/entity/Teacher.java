package maktab.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher extends User {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Teacher_Course",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "teacher")
    private List<Exam> exams;
    @OneToMany(mappedBy = "teacher")
    private List<Question> questions;
    @Transient
    private Boolean hasCourse;
    public Teacher() {
        setAuthority("teacher");
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

    public Boolean getHasCourse() {
        return hasCourse;
    }

    public void setHasCourse(Boolean hasCourse) {
        this.hasCourse = hasCourse;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
