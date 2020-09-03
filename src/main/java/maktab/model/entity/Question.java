package maktab.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "^.{1,}$" , message="question should be filled.")
    private String question;
    private QuestionType questionType;
    @ElementCollection
    private List<String> answers;
    @Pattern(regexp = "^.{1,}$" , message="correct answer should be filled.")
    private String correctAnswer;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
