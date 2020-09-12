package maktab.model.dao;

import maktab.model.entity.Admin;
import maktab.model.entity.Course;
import maktab.model.entity.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CourseDao extends Repository<Course, Integer>, JpaSpecificationExecutor<Course> {
    Course save(Course course);
    Optional<Course> findByNumber(int number);
    List<Course> findByAdmin(Admin admin);

}
