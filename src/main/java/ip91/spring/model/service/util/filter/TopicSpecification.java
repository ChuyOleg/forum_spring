package ip91.spring.model.service.util.filter;

import ip91.spring.model.entity.Topic;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TopicSpecification implements Specification<Topic> {

    private final List<SearchCriteria> criteriaList;

    public TopicSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case EQUAL: predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                case IN: predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
