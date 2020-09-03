package maktab.service;

import maktab.model.dao.CourseDao;
import maktab.model.entity.Course;
import maktab.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Transactional
    public void addNewCourse(Course course) throws Exception {
        Optional<Course> found = courseDao.findByNumber(course.getNumber());
        if (!found.isPresent()) {
            courseDao.save(course);
        } else
            throw new Exception("course with this identifier is exist");
    }
}
