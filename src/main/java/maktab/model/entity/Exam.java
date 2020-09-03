package maktab.model.entity;

import com.mysql.cj.protocol.x.XMessage;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
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
    private String timer;
    @Pattern(regexp="^[0-2]{1}[0-9]{3}/(0?[1-9]|1[0-2])/([0]?[1-9]|[1-2]?[1-9]|3?[0-1]) ([0-1]?[0-9]|[2]?[0-4]):[0-5][0-9]:[0-5][0-9]$",message="please match the format:yy/MM/dd hh:mm:ss")
    private LocalDateTime theBeginning;
    @Pattern(regexp="^[0-2]{1}[0-9]{3}/(0?[1-9]|1[0-2])/([0]?[1-9]|[1-2]?[1-9]|3?[0-1]) ([0-1]?[0-9]|[2]?[0-4]):[0-5][0-9]:[0-5][0-9]$",message="please match the format:yy/MM/dd hh:mm:ss")
    private LocalDateTime theEnd;
    //private Map<Student,List<Result>> studentResultMap;


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

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public LocalDateTime getTheBeginning() {
        return theBeginning;
    }

    public void setTheBeginning(LocalDateTime theBeginning) {
        this.theBeginning = theBeginning;
    }

    public LocalDateTime getTheEnd() {
        return theEnd;
    }

    public void setTheEnd(LocalDateTime theEnd) {
        this.theEnd = theEnd;
    }
}
