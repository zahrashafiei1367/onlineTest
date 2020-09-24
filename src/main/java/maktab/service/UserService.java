package maktab.service;
import maktab.aspect.log;
import maktab.model.dao.UserDao;
import maktab.model.dao.UserSpecification;
import maktab.model.entity.Admin;
import maktab.model.entity.Student;
import maktab.model.entity.Teacher;
import maktab.model.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;




    @Transactional
    public User findById(int id) throws Exception {
        Optional<User> found = userDao.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else
            throw new Exception("user not found.");
    }

    @log
    @Transactional
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        Optional<User> found = userDao.findByUsernameAndPassword(username, password);
        if (found.isPresent()) {
            return found.get();
        } else{
            throw new Exception("user not found.");
        }

    }

    @Transactional
    public void update(User user) throws Exception {
        userDao.update(user.getId(), user.getUsername(), user.getName(), user.getFamily(), user.getPassword(), user.getEnabled());
    }

    @Transactional
    public void deleteById(int id) throws Exception {
        Optional<User> found = userDao.findById(id);
        if (found.isPresent()) {
            userDao.delete(found.get());
        } else
            throw new Exception("user by id: " + id + " not exist!");
    }

    @Transactional
    public List<User> findMaxMatch(String name,
                                   String family,
                                   String username, Admin admin) {
        return userDao.findAll(UserSpecification.findMaxMatch(name, family, username));


    }

    public Admin getAdmin(User user) throws Exception {
        if (user.getAuthority().equals("teacher")) {
            Teacher teacher = (Teacher) user;
            return teacher.getAdmin();
        } else if (user.getAuthority().equals("student")) {
            Student student = (Student) user;
            return student.getAdmin();
        } else
            throw new Exception("user is admin itself");

    }
}
