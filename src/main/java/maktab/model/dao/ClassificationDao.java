package maktab.model.dao;

import maktab.model.entity.Admin;
import maktab.model.entity.Classification;
import maktab.model.entity.Course;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassificationDao extends Repository<Classification, Integer>, JpaSpecificationExecutor<Classification> {
    Classification save(Classification classification);
    List<Classification> findAll();
    Optional<Classification> findByValue(String value);
    Optional<Classification> findById(int id);
    @Modifying
    @Query("update Classification set value=:newValue where id=:id")
    void update(@Param("id") int id, @Param("newValue") String newValue);

}
