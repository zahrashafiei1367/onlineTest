package maktab.service;

import maktab.model.dao.StudentDao;
import maktab.model.dao.StudentSpecifications;
import maktab.model.entity.Admin;
import maktab.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;



    @Transactional
    public Student findByUsernameAndPassword(String username, String password) throws Exception {
        Optional<Student> found = studentDao.findByUsername(username);
        if (found.isPresent()) {
            found = studentDao.findByUsernameAndPassword(username, password);
            if (found.isPresent())
                return found.get();
            else
                throw new Exception("password is not correct.");
        } else throw new Exception("username does not exist");
    }

    @Transactional(readOnly = true)
    public List<Student> showAllStudent(Admin admin) {
        return studentDao.findByAdmin(admin);
    }


    @Transactional
    public void registerNewStudent(Student student) throws Exception {
        Optional<Student> found = studentDao.findByUsername(student.getUsername());
        if (!found.isPresent()) {
            studentDao.save(student);
        } else
            throw new Exception("account with this email is exist!");
    }


    @Transactional
    public List<Student> findMaxMatch(String name,
                                      String family,
                                      String username) {
        return studentDao.findAll(StudentSpecifications.findMaxMatch(name, family, username));
    }

}
