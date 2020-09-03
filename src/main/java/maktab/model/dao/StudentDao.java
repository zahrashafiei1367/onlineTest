package maktab.model.dao;

import maktab.model.entity.Admin;
import maktab.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@Transactional
public interface StudentDao extends UserBaseDao<Student>, JpaSpecificationExecutor<Student>  {
   Student save(Student s);
    List<Student> findByAdmin(Admin admin);
}
