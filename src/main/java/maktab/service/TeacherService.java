package maktab.service;

import maktab.model.dao.TeacherDao;
import maktab.model.dao.TeacherSpecification;
import maktab.model.entity.Admin;
import maktab.model.entity.Course;
import maktab.model.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Teacher findByUsernameAndPassword(String username, String password) throws Exception {
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
    public List<Teacher> showAllTeachers(Admin admin) {
        return teacherDao.findByAdmin(admin);
    }

    public List<Teacher> findMaxMatch(String name,
                                      String family,
                                      String username, Admin admin) {
        List<Teacher> teachers = teacherDao.findAll(TeacherSpecification.findMaxMatch(name, family, username));
        List<Teacher> adminTeachers = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {
            Teacher teacher = teachers.get(i);
            if (teacher.getAdmin().getId() == admin.getId())
                adminTeachers.add(teacher);
        }
        return adminTeachers;
    }


    @Transactional
    public void addOrRemoveCourse(int userId, Course course) throws Exception {
        Teacher teacher = findById(userId);
        List<Course> courses = teacher.getCourses();
        if (courses.contains(course))
            courses.remove(course);
        else
            courses.add(course);
    }

    @Transactional
    public Teacher findById(int id) throws Exception {
        Optional<Teacher> found = teacherDao.findById(id);
        if (found.isPresent())
            return found.get();
        else
            throw new Exception("teacher with this id not found");

    }

    @Transactional
    public List<Teacher> showAllTeachersHasCourse(Admin admin, Course course) {
        List<Teacher> teachers = teacherDao.findByAdmin(admin);
        List<Teacher> teachersThatHasCourse = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getCourses().contains(course))
                teachersThatHasCourse.add(teacher);
        }
        return teachersThatHasCourse;
    }


}
