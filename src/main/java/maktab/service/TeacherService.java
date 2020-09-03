package maktab.service;

import maktab.model.dao.StudentSpecifications;
import maktab.model.dao.TeacherDao;
import maktab.model.dao.TeacherSpecification;
import maktab.model.entity.Admin;
import maktab.model.entity.Student;
import maktab.model.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Transactional
    public void registerNewTeacher(Teacher teacher) throws Exception {
        Optional<Teacher> found = teacherDao.findByUsername(teacher.getUsername());
        if (!found.isPresent()) {
            teacherDao.save(teacher);
        } else
            throw new Exception("account with this email is exist!");
    }

    @Transactional
    public Teacher findByUsernameAndPassword(String username,String password) throws Exception {
        Optional<Teacher> found = teacherDao.findByUsername(username);
        if (found.isPresent()) {
            found = teacherDao.findByUsernameAndPassword(username, password);
            if (found.isPresent())
                return found.get();
            else
                throw new Exception("password is not correct.");
        } else throw new Exception("username does not exist");
    }

    @Transactional(readOnly = true)
    public List<Teacher> showAllTeachers(Admin admin){
        return teacherDao.findByAdmin(admin);
    }

    public List<Teacher> findMaxMatch(String name,
                                      String family,
                                      String username){
        return teacherDao.findAll(TeacherSpecification.findMaxMatch(name, family,username));
    }
}
