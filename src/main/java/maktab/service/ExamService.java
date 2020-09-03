package maktab.service;

import maktab.model.dao.ExamDao;
import maktab.model.entity.Course;
import maktab.model.entity.Exam;
import maktab.model.entity.Question;
import maktab.model.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ExamDao examDao;

    @Transactional
    public void addNewExam(Exam exam){
        examDao.save(exam);
    }
}
