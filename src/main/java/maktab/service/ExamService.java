package maktab.service;

import maktab.model.dao.ExamDao;
import maktab.model.entity.Course;
import maktab.model.entity.Exam;
import maktab.model.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ExamDao examDao;

    @Transactional
    public void addNewExam(Exam exam) {
        examDao.save(exam);
    }

    @Transactional
    public List<Exam> showAllCourseExams(Course course) {
        return examDao.findByCourse(course);
    }

    @Transactional
    public List<Exam> showAllTeacherExams(Teacher teacher) {
        return examDao.findByTeacher(teacher);
    }

    public Exam findById(int id) throws Exception {
        Optional<Exam> found=examDao.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else
            throw new Exception("exam with this id is not exist!");
    }
}
