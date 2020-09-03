package maktab.model.dao;

import maktab.model.entity.User;
import maktab.model.entity.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public interface UserDao extends UserBaseDao<User>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username,String password);
    Optional<User> findById(int id);
    void delete(User user);

    User save(User u);
    @Modifying
    @Query("update User set username=:newUsername,name=:newName ,family=:newFamily,password=:newPass,enabled=:newEnabled where username=:username")
    void update(@Param("username") String username, @Param("newUsername") String newUsername,@Param("newName") String name,@Param("newFamily") String family,@Param("newPass") String pass,@Param("newEnabled") Boolean enable);
}


