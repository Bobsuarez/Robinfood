package com.robinfood.paymentmethodsbc.specifications;


import com.robinfood.paymentmethodsbc.model.Entity;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;


public final class TransactionSpecifications {
    private static final String ENTITI_SOURCE_FIELD = "entitySource";
    private static final String ENTITI_REFERENCE_FIELD = "entityReference";
    private static final String UUID_FIELD = "uuid";

    private TransactionSpecifications() {}

    public static Specification<Transaction> getEqualTransactionEntity(Long entitySource, String entityReference) {
        return getTransactionLongEqual(entitySource, ENTITI_SOURCE_FIELD).or(getTransactionDetailLongEqual(
            entityReference,
            ENTITI_REFERENCE_FIELD
        ));
    }

    public static Specification<Transaction> getEqualTransactionEntity(
        Long entitySource, 
        String entityReference, 
        String uuid
    ) {
        return getTransactionLongEqual(entitySource, ENTITI_SOURCE_FIELD)
        .or(getTransactionStringEqual(uuid, UUID_FIELD))
        .or(
            getTransactionDetailLongEqual(
            entityReference,
            ENTITI_REFERENCE_FIELD
        ));
    }

    public static Specification<Transaction> getTransactionDetailLongEqual(
        final String equal, final String fieldName
    ) {
        return (Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (Objects.isNull(equal) || equal.isBlank()) {
                return null;
            }
            return criteriaBuilder.equal(
                root.<TransactionDetail>get("transactionDetail").<String>get(fieldName),
                equal
            );
        };
    }


    public static Specification<Transaction> getTransactionLongEqual(
        final Long equal, final String fieldName
    ) {
        return (Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (Objects.isNull(equal)) {
                return null;
            }
            return criteriaBuilder.equal(root.get(fieldName), equal);
        };
    }

    public static Specification<Transaction> getEntityLongEqual(
        final Long value, final String entityFieldName
    ) {
        return (Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (Objects.isNull(value)) {
                return null;
            }
            return criteriaBuilder.equal(root.<Entity>get("entity").<Long>get(entityFieldName), value);
        };
    }

    public static Specification<Transaction> getTransactionStringEqual(
        final String equal, final String fieldName
    ) {
        return (Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (Objects.isNull(equal)) {
                return null;
            }
            return criteriaBuilder.equal(root.get(fieldName), equal);
        };
    }
}
