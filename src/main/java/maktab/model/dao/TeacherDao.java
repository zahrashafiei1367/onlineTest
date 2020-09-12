package maktab.model.dao;

import maktab.model.entity.Admin;
import maktab.model.entity.Course;
import maktab.model.entity.Student;
import maktab.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public interface TeacherDao extends UserBaseDao<Teacher>, JpaSpecificationExecutor<Teacher> {
    Teacher save(Teacher t);
    List<Teacher> findByAdmin(Admin admin);
    List<Teacher> findByCourses(Course course);
}
