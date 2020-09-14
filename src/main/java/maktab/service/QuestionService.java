package maktab.service;

import maktab.model.dao.QuestionDao;
import maktab.model.entity.Exam;
import maktab.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Transactional
    public List<Question> findByExam(Exam exam){
        return questionDao.findByExam(exam);
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
    public void addAQuestion(Question question){
        questionDao.save(question);
    }

}
