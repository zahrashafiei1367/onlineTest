package maktab.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExamBuilder {
    private Course course;
    private Teacher teacher;
    private List<Question> questions;
    private String timer;
    private String theBeginning;
    private String theEnd;
    public static ExamBuilder anExam(){
        return new ExamBuilder();
    }

    public ExamBuilder WithCourse(Course course) {
        this.course = course;
        return this;
    }

    public ExamBuilder WithTeacher(Teacher teacher) {
        this.teacher=teacher;
        return this;
    }

    public ExamBuilder WithTimer(String timer) {
        this.timer=timer;
        return this;
    }

    public ExamBuilder WithTheBeginning(String theBeginning) {
        this.theBeginning=theBeginning;
        return this;
    }

    public ExamBuilder WithTheEnd(String theEnd) {
        this.theEnd=theEnd;
        return this;
    }

    public ExamBuilder WithQuestions(List<Question> questions) {
        this.questions=questions;
        return this;
    }

    public Exam build(){
        Exam exam=new Exam();
        exam.setCourse(course);
        exam.setTeacher(teacher);
        exam.setTheBeginning(theBeginning);
        exam.setTheEnd(theEnd);
        exam.setTimer(timer);
        exam.setQuestions(questions);
        return exam;
    }
}
