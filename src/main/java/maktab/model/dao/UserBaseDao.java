package maktab.model.dao;

import maktab.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserBaseDao<T extends User> extends CrudRepository<T, Long> {
        Optional<T> findByUsername(String username);
        Optional<T> findByUsernameAndPassword(String username,String password);
}

