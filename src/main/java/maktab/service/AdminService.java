package maktab.service;

import maktab.aspect.log;
import maktab.model.dao.AdminDao;
import maktab.model.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    @Transactional
    public void addNewAdmin(Admin admin) throws Exception {
        Optional<Admin> found = adminDao.findByUsername(admin.getUsername());
        if (!found.isPresent()) {
            found = adminDao.findBySchoolName(admin.getSchoolName());
            if (!found.isPresent()) {
                adminDao.save(admin);
            } else throw new Exception("school name is duplicated.");
        } else
            throw new Exception("account with this email is exist!");
    }
    @log
    @Transactional
    public Admin findByUsernameAndPassword(String username, String password) throws Exception {
        Optional<Admin> found = adminDao.findByUsername(username);
        if (found.isPresent()) {
            found = adminDao.findByUsernameAndPassword(username, password);
            if (found.isPresent())
                return found.get();
            else
                throw new Exception("password is not correct.");
        } else throw new Exception("username does not exist");
    }

    @Transactional(readOnly = true)
    public List<Admin> showAllAdmins() {
        return adminDao.findAll();
    }

    @Transactional
    public Admin findBySchoolName(String schoolName) throws Exception {
        Optional<Admin> found = adminDao.findBySchoolName(schoolName);
        if (found.isPresent())
            return found.get();
        else
            throw new Exception("school name did not found.");
    }

    @Transactional
    public Admin findById(int id) throws Exception {
        Optional<Admin> found = adminDao.findById(id);
        if (found.isPresent())
            return found.get();
        else
            throw new Exception("school name did not found.");
    }
}

