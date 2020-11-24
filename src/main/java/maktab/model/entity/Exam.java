package maktab.model.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToMany(mappedBy = "exam")
    private List<Question> questions;
    @Pattern(regexp="^([0-1]?[0-9]|[2]?[0-4]):[0-5][0-9]:[0-5][0-9]$",message="please match the format:hh:mm:ss")
    private String examDuration;
    @Pattern(regexp="^[0-2]{1}[0-9]{3}/(0?[1-9]|1[0-2])/([0]?[1-9]|[1-2]?[1-9]|3?[0-1]) ([0-1]?[0-9]|[2]?[0-4]):[0-5][0-9]:[0-5][0-9]$",message="please match the format:yy/MM/dd hh:mm:ss")
    private String theBeginning;
    @Pattern(regexp="^[0-2]{1}[0-9]{3}/(0?[1-9]|1[0-2])/([0]?[1-9]|[1-2]?[1-9]|3?[0-1]) ([0-1]?[0-9]|[2]?[0-4]):[0-5][0-9]:[0-5][0-9]$",message="please match the format:yy/MM/dd hh:mm:ss")
    private String theEnd;
    @OneToMany(mappedBy = "exam")
    private List<Result> results;
    @NotNull( message = "*title should be filled.")
    private String title;
    @NotNull( message = "*explanation should be filled.")
    private String explanation;
    @Value(value = "false")
    private Boolean isStopped;

    public Exam() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(String examDuration) {
        this.examDuration = examDuration;
    }

    public String getTheBeginning() {
        return theBeginning;
    }

    public void setTheBeginning(String theBeginning) {
        this.theBeginning = theBeginning;
    }

    public String getTheEnd() {
        return theEnd;
    }

    public void setTheEnd(String theEnd) {
        this.theEnd = theEnd;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Boolean getStopped() {
        return isStopped;
    }

    public void setStopped(Boolean stopped) {
        isStopped = stopped;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", course=" + course.getTitle() +
                ", teacher=" + teacher.getUsername() +
                ", exam duration='" + examDuration + '\'' +
                ", theBeginning='" + theBeginning + '\'' +
                ", theEnd='" + theEnd + '\'' +
                ", title='" + title + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
