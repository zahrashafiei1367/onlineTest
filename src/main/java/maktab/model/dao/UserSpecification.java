package maktab.model.dao;

import maktab.model.entity.Teacher;
import maktab.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface UserSpecification {
    static Specification<User> findMaxMatch(String name,
                                               String family,
                                               String username){
        return (Specification<User>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<User> resultCriteria = builder.createQuery(User.class);

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
