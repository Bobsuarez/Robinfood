package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.domain.ArticleTaxDetail;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ArticleFamily;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleFamilyService {

    ArticleFamily create(ArticleFamily articleFamily)
        throws JsonProcessingException, BusinessRuleException;

    Page<ArticleFamily> list(
        Long productTypeId, Long articleId, Integer status, Pageable pageable);

    List<ArticleTaxDetail> findDetail(Long articleId, Long productType);

    void delete(Long id) throws JsonProcessingException, BusinessRuleException;
}
