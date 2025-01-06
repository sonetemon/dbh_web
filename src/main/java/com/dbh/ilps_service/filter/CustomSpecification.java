package com.dbh.ilps_service.filter;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomSpecification<T> implements Specification<T> {
    private final List<FilterCriteria> criteriaList;

    public CustomSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(FilterCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (FilterCriteria criteria : criteriaList) {
            String key = criteria.getKey();
            Object value = criteria.getValue();
            String operation = criteria.getOperation();

            switch (operation) {
                case "=":
                    predicates.add(builder.equal(root.get(key), value));
                    break;
                case "!=":
                    predicates.add(builder.notEqual(root.get(key), value));
                    break;
                case ">":
                    predicates.add(builder.greaterThan(root.get(key), (Comparable) value));
                    break;
                case ">=":
                    predicates.add(builder.greaterThanOrEqualTo(root.get(key), (Comparable) value));
                    break;
                case "<":
                    predicates.add(builder.lessThan(root.get(key), (Comparable) value));
                    break;
                case "<=":
                    predicates.add(builder.lessThanOrEqualTo(root.get(key), (Comparable) value));
                    break;
                case "LIKE":
                    predicates.add(builder.like(root.get(key), "%" + value + "%"));
                    break;
                case "BETWEEN":
                    if (value instanceof List && ((List<?>) value).size() == 2) {
                        predicates.add(builder.between(root.get(key),
                                (Comparable) ((List<?>) value).get(0),
                                (Comparable) ((List<?>) value).get(1)));
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("Operation not supported: " + operation);
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
