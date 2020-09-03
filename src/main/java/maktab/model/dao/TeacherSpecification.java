package maktab.model.dao;

import maktab.model.entity.Student;
import maktab.model.entity.Teacher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface TeacherSpecification {
    static Specification<Teacher> findMaxMatch(String name,
                                               String family,
                                               String username){
        return (Specification<Teacher>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<Teacher> resultCriteria = builder.createQuery(Teacher.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            if(!name.equals("")){
                predicates.add(builder.equal(root.get("name"), name));
            }
            if(!family.equals("")){
                predicates.add(builder.equal(root.get("family"), family));
            }
            if(!username.equals("")){
                predicates.add(builder.equal(root.get("username"), username));
            }

            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };

    }
}
