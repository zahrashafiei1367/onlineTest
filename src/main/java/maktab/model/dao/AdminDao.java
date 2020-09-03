package maktab.model.dao;

import maktab.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface AdminDao extends UserBaseDao<Admin>, JpaSpecificationExecutor<Admin> {
    Admin save(Admin admin);

    Optional<Admin> findBySchoolName(String schoolName);

    List<Admin> findAll();

    Optional<Admin> findById(int id);

}