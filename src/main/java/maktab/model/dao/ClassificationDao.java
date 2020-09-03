package maktab.model.dao;

import maktab.model.entity.Classification;
import maktab.model.entity.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ClassificationDao extends Repository<Classification, Integer>, JpaSpecificationExecutor<Classification> {
    Classification save(Classification classification);
    List<Classification> findAll();
    Optional<Classification> findByValue(String value);
}
