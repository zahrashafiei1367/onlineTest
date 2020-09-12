package maktab.service;

import maktab.model.dao.ExamDao;
import maktab.model.entity.Course;
import maktab.model.entity.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamDao examDao;

    @Transactional
    public void addNewExam(Exam exam) {
        examDao.save(exam);
    }

    @Transactional
    public List<Exam> showAllByCourse(Course course) {
        return examDao.findByCourse(course);
    }
}
