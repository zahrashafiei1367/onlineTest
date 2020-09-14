package maktab.model.dao;

import maktab.model.entity.Course;
import maktab.model.entity.Exam;
import maktab.model.entity.Teacher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ExamDao extends Repository<Exam, Integer>, JpaSpecificationExecutor<Exam> {
    Exam save(Exam exam);
    List<Exam> findByCourse(Course course);
    List<Exam> findByTeacher(Teacher teacher);
    Optional<Exam> findById(int id);
}
