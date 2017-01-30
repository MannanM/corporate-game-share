package com.mannanlive.builder;

import com.mannanlive.model.SearchCriterion;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpecificationBuilder<T> {
    public Specification<T> createSpecification(final List<SearchCriterion> searchCriteria) {
        return (root, query, criteriaBuilder) -> buildSpecification(searchCriteria, root, query, criteriaBuilder);
    }

    private Predicate buildSpecification(List<SearchCriterion> searchCriteria, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriterion searchCriterion : searchCriteria) {
            if (searchCriterion.getValue() != null) {
                generatePredicate(root, criteriaBuilder, predicates, searchCriterion);
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private void generatePredicate(Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, SearchCriterion searchCriterion) {
        if (searchCriterion.getOperation() == "equal") {
            predicates.add(attributeEquals(root, criteriaBuilder, searchCriterion));
        } else if (searchCriterion.getOperation() == "like") {
            predicates.add(attributeIsLike(root, criteriaBuilder, searchCriterion));
        } else if (searchCriterion.getOperation() == "contains")  {
            predicates.add(listOfAttributesContains(root, criteriaBuilder, searchCriterion));
        } else if (searchCriterion.getOperation() == "join") {
            predicates.add(secondaryAttributeEquals(root, criteriaBuilder, searchCriterion));

        }
    }

    private Predicate listOfAttributesContains(Root<T> root, CriteriaBuilder criteriaBuilder, SearchCriterion searchCriterion) {
        Expression<Collection<Object>> collectionOfAttributes = root.get(searchCriterion.getKey());
        Object value = searchCriterion.getValue();
        return criteriaBuilder.isMember(value, collectionOfAttributes);
    }

    private Predicate secondaryAttributeEquals(Root<T> root, CriteriaBuilder criteriaBuilder, SearchCriterion searchCriterion) {
        Path<Object> attribute = root.get(searchCriterion.getKey()).get(searchCriterion.getSecondaryKey());
        Object value = searchCriterion.getValue();
        return criteriaBuilder.equal(attribute, value);
    }

    private Predicate attributeEquals(Root<T> root, CriteriaBuilder criteriaBuilder, SearchCriterion searchCriterion) {
        Path<Object> attribute = root.get(searchCriterion.getKey());
        Object value = searchCriterion.getValue();
        return criteriaBuilder.equal(attribute, value);
    }

    private Predicate attributeIsLike(Root<T> root, CriteriaBuilder criteriaBuilder, SearchCriterion searchCriterion) {
        Expression<String> attribute = criteriaBuilder.lower(root.get(searchCriterion.getKey()));
        String value = searchCriterion.getValue().toString().toLowerCase();
        return criteriaBuilder.like(attribute, "%"+value+"%");

    }
}