package maktab.model.dao;

import maktab.model.entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.*;

public interface StudentSpecifications {
    static Specification<Student> findMaxMatch(String studentName,
                                               String studentFamily,
                                               String username){
        return (Specification<Student>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<Student> resultCriteria = builder.createQuery(Student.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            if(!studentName.equals("")){
                predicates.add(builder.equal(root.get("name"), studentName));
            }
            if(!studentFamily.equals("")){
                predicates.add(builder.equal(root.get("family"), studentFamily));
            }
            if(!username.equals("")){
                predicates.add(builder.equal(root.get("username"), username));
            }

            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };

    }
}
