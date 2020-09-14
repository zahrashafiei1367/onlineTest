package maktab.service;

import maktab.model.dao.CourseDao;
import maktab.model.entity.Admin;
import maktab.model.entity.Classification;
import maktab.model.entity.Course;
import maktab.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Transactional
    public void addNewCourse(Course course) throws Exception {
        Optional<Course> found = courseDao.findByNumber(course.getNumber());
        if (!found.isPresent()) {
            course.setId(0);
            courseDao.save(course);
        } else
            throw new Exception("course with this number is exist");
    }

    @Transactional(readOnly = true)
    public List<Course> showAllCourses(Admin admin) {
        return courseDao.findByAdmin(admin);
    }

    @Transactional
    public Course findByNumber(int number) throws Exception {
        Optional<Course> found = courseDao.findByNumber(number);
        if (found.isPresent())
                return found.get();
            else
                throw new Exception("course with this number not found.");
    }


}
