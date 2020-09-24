package maktab.model.entity;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class QuestionBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "^.{1,}$", message = "question should be filled.")
    private String question;
    private QuestionType questionType;
    @Embedded@ElementCollection()
    private List<String> answers;
    @Pattern(regexp = "^.{1,}$", message = "correct answer should be filled.")
    private String correctAnswer;
    @Pattern(regexp = "^.{1,}$", message = "title should be filled.")
    private String title;
    @ManyToOne
    @JoinColumn(name = "classification_id")
    private Classification classification;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "QuestionBank{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", questionType=" + questionType +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", title='" + title + '\'' +
                ", classification=" + classification +
                ", teacher=" + teacher.getName() +
                '}';
    }
}
