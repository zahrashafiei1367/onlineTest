package maktab.model.dao;

import maktab.model.entity.Classification;
import maktab.model.entity.Question;
import maktab.model.entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.*;

public interface QuestionSpecification {
    static Specification<Question> findMaxMatch(String question,
                                                String title,
                                                String classification){

        return (Specification<Question>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<Question> resultCriteria = builder.createQuery(Question.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            if(!question.equals("")){
                predicates.add(builder.like(root.get("question"), "%"+question+"%"));
            }
            if(!title.equals("")){
                predicates.add(builder.like(root.get("title"), "%"+title+"%"));
            }
            if(!classification.equals("")){
                predicates.add(builder.like(root.get("classification").get("value"),classification));
            }

            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };

    }
}
