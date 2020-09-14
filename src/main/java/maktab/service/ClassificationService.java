package maktab.service;

import maktab.model.dao.ClassificationDao;
import maktab.model.dao.CourseDao;
import maktab.model.entity.Admin;
import maktab.model.entity.Classification;
import maktab.model.entity.Course;
import maktab.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;


    @Transactional
    public void addNewClassification(Classification classification) throws Exception {
        Optional<Classification> found=classificationDao.findByValue(classification.getValue());
        if(!found.isPresent())
            classificationDao.save(classification);
        else throw new Exception("classification is exist");
    }

    @Transactional
    public List<Classification> findAll(){
        return classificationDao.findAll();
    }

    @Transactional
    public Classification findByValue(String value) throws Exception {
        Optional<Classification> found=classificationDao.findByValue(value);
        if(found.isPresent())
            return found.get();
        else throw new Exception("classification is not exist.");
    }

    @Transactional
    public Classification findById(int id) throws Exception {
        Optional<Classification> found=classificationDao.findById(id);
        if(found.isPresent())
            return found.get();
        else throw new Exception("classification is not exist.");
    }

    @Transactional
    public void update(int id,String value) throws Exception
    {
        classificationDao.update(id,value);
    }
}
