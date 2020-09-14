package maktab.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Question extends QuestionBank{
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    @OneToMany(mappedBy = "question")
    private List<Result> results;
    @NotNull(message = "please choose if you want to save question in bank Question or not.")
    private boolean addToQuestionBank;
    @Transient
    private String ans;
    public Question() {
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public boolean isAddToQuestionBank() {
        return addToQuestionBank;
    }

    public void setAddToQuestionBank(boolean addToQuestionBank) {
        this.addToQuestionBank = addToQuestionBank;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
