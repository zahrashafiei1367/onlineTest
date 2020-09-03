package maktab.service;

import maktab.model.dao.TeacherSpecification;
import maktab.model.dao.UserDao;
import maktab.model.dao.UserSpecification;
import maktab.model.entity.Student;
import maktab.model.entity.Teacher;
import maktab.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void registerNewUser(User user) throws Exception {
        Optional<User> found = userDao.findByUsername(user.getUsername());
        if(!found.isPresent()){
            userDao.save(user);
        }
        else
            throw new Exception("account with this email is exist!");
    }
    @Transactional
    public User findByUsername(String username) throws Exception {
        Optional<User> found = userDao.findByUsername(username);
        if(found.isPresent()){
            return found.get();
        }
        else
            throw new Exception("user not found.");
    }

    @Transactional
    public User findById(int id) throws Exception {
        Optional<User> found = userDao.findById(id);
        if(found.isPresent()){
            return found.get();
        }
        else
            throw new Exception("user not found.");
    }

    @Transactional
    public User findByUsernameAndPassword(String username,String password) throws Exception {
        Optional<User> found = userDao.findByUsernameAndPassword(username,password);
        if(found.isPresent()){
            return found.get();
        }
        else
            throw new Exception("user not found.");
    }

    @Transactional
    public void editByUsername(User user,String username) throws Exception {
        Optional<User> found = userDao.findByUsername(username);
        if(found.isPresent()){
            if(user.getUsername().equals(""))
                user.setUsername(found.get().getUsername());
            if(user.getName().equals(""))
                user.setName(found.get().getName());
            if(user.getFamily().equals(""))
                user.setFamily(found.get().getFamily());
            if(user.getPassword().equals(""))
                user.setPassword(found.get().getPassword());
            if(user.getAuthority().equals(""))
                user.setAuthority(found.get().getAuthority());
            userDao.update(username,user.getUsername(),user.getName(),user.getFamily(),user.getPassword(),user.getEnabled());
        }
        else throw new Exception("user with this username is not exist");
    }

    @Transactional
    public void deleteById(int id) throws Exception {
        Optional<User> found = userDao.findById(id);
        if(found.isPresent()){
            userDao.delete(found.get());
        }
        else
            throw new Exception("user by id: "+id+" not exist!");
    }
    @Transactional
    public List<User> findMaxMatch(String name,
                                   String family,
                                   String username){
        return userDao.findAll(UserSpecification.findMaxMatch(name, family,username));
    }
}
