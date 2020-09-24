package maktab.service;

import maktab.model.dao.QuestionDao;
import maktab.model.dao.QuestionSpecification;
import maktab.model.dao.StudentSpecifications;
import maktab.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Transactional
    public Map<Question,List<String>> findByExam(Exam exam){
        Map<Question,List<String>> questionListMap=new HashMap<>();
        List<Question> questions = questionDao.findByExam(exam);
        for(Question question:questions){
            List<String> answers=question.getAnswers();
            List<String> ans=new ArrayList<>();
            if(Objects.isNull(answers))
                answers=new ArrayList<>();
            for(String answer:answers){
                ans.add(answer);
            }
            questionListMap.put(question,ans);
        }
        return questionListMap;
    }

    @Transactional
    public Map<Question,List<String>> findMaxMatch(String q,
                                      String title,
                                      String classification) {
        Map<Question,List<String>> questionListMap=new HashMap<>();
        List<Question> questions=questionDao.findAll(QuestionSpecification.findMaxMatch(q,title,classification));
        for(Question question:questions){
            List<String> answers=question.getAnswers();
            List<String> ans=new ArrayList<>();
            if(Objects.isNull(answers))
                answers=new ArrayList<>();
            for(String answer:answers){
                ans.add(answer);
            }
            questionListMap.put(question,ans);
        }
        return questionListMap;
    }

    @Transactional
    public Question findById(int id) throws Exception {
        Optional<Question> found=questionDao.findById(id);
        if (found.isPresent())
            return found.get();
        else
            throw new Exception("question with this id not found");
    }

    @Transactional
    public Question addAQuestion(Question question){
        return questionDao.save(question);
    }

    @Transactional
    public Question addAnswer(int qid, String answer) throws Exception {
        Question question=findById(qid);
        List<String> answers=getAnswers(qid);
        if(Objects.isNull(answers))
            answers=new ArrayList<>();
        answers.add(answer);
        question.setAnswers(answers);
        return question;
    }
    @Transactional
    public Question addAQuestionFromBank(Question question, Exam exam) throws Exception {
        Question question1=new Question();
        question1.setExam(exam);
        question1.setQuestionType(question.getQuestionType());
        question1.setQuestion(question.getQuestion());
        question1.setTeacher(question.getTeacher());
        question1.setAddToQuestionBank(question.isAddToQuestionBank());
        question1.setClassification(question.getClassification());
        question1.setCorrectAnswer(question.getCorrectAnswer());
        List<String> answers=getAnswers(question.getId());
        List<String> newAnswer=new ArrayList<>();
        if(Objects.isNull(answers))
            answers=new ArrayList<>();
        for(String answer:answers){
            newAnswer.add(answer);
        }
        question1.setAnswers(newAnswer);
        return addAQuestion(question1);
    }

    @Transactional
    public List<String> getAnswers(int questionId) throws Exception {
            Question found=findById(questionId);
            return found.getAnswers();
    }
}
