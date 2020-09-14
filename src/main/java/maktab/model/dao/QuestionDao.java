package maktab.model.dao;

import maktab.model.entity.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface QuestionDao extends Repository<Question, Integer>, JpaSpecificationExecutor<Question>{
    Question save(Question q);
    List<Question> findByExam(Exam exam);
    Optional<Question> findById(int id);
}
