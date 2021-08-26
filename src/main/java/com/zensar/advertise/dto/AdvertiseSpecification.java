package com.zensar.advertise.dto;

import com.zensar.advertise.entity.Advertise;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class AdvertiseSpecification implements Specification<Advertise> {

    private final Advertise advertise;

    public AdvertiseSpecification(Advertise advertise) {
        super();
        this.advertise = advertise;
    }

    @Override
    public Predicate toPredicate(Root<Advertise> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.disjunction();

        if (advertise.getCategory() !=null)
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("category"),advertise.getCategory()));
        if (advertise.getStatus() !=null)
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("status"),advertise.getStatus()));
        if (advertise.getTitle() !=null)
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("title"),advertise.getTitle()));
        if (advertise.getPrice() !=null)
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("price"),advertise.getPrice()));
        if (advertise.getCreatedBy() !=null)
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("createdBy"),advertise.getCreatedBy()));
        if (advertise.getCreatedAt() !=null)
        predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdAt"),advertise.getCreatedAt()));
        return predicate;
    }
}
