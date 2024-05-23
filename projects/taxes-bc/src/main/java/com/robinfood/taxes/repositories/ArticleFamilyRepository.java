package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.ArticleFamily;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleFamilyRepository extends SoftDeleteRepository<ArticleFamily, Long> {

    @Query("SELECT article "
        + "FROM ArticleFamily article, "
        + "Tax tax, "
        + "Family family "
        + "WHERE article.deletedAt IS NULL "
        + "AND article.family.id = family.id "
        + "AND tax.family.id = family.id "
        + "AND article.articleId = :articleId "
        + "AND article.productTypeId = :productType "
        + "AND article.status = :status "
        + "AND family.status = :status "
        + "AND tax.status = :status")
    List<ArticleFamily> findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(
        Long productType, Long articleId, Integer status);

    boolean existsByFamilyIdAndProductTypeIdAndArticleIdAndDeletedAtIsNull(
        Long familyId, Long productTypeId, Long articleId);

    @Query("SELECT a FROM ArticleFamily a "
        + "WHERE (:productTypeId IS NULL OR a.productTypeId = :productTypeId) "
        + "AND (:articleId IS NULL OR a.articleId = :articleId) "
        + "AND (:status IS NULL OR a.status = :status) "
        + "AND a.deletedAt IS NULL")
    Page<ArticleFamily> findPageByProductTypeIdAndArticleIdAndStatus(
        Long productTypeId, Long articleId, Integer status, Pageable pageable);

}
