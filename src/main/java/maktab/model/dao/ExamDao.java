package maktab.model.dao;

import maktab.model.entity.Course;
import maktab.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface ExamDao extends Repository<Exam, Integer>, JpaSpecificationExecutor<Exam> {
    Exam save(Exam exam);
}
