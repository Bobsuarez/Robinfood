package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.GeneralConstants.ACTIVE_STATUS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.domain.ArticleTaxDetail;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.repositories.ArticleFamilyRepository;
import com.robinfood.taxes.repositories.FamilyRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.services.ArticleFamilyService;
import com.robinfood.taxes.services.AuditLogService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class ArticleFamilyServiceImpl implements ArticleFamilyService {

    private final FamilyRepository familyRepository;

    private final TaxRepository taxRepository;

    private final ArticleFamilyRepository articleFamilyRepository;

    private final AuditLogService auditLogService;

    public ArticleFamilyServiceImpl(
        FamilyRepository familyRepository,
        TaxRepository taxRepository,
        final ArticleFamilyRepository articleFamilyRepository,
        final AuditLogService auditLogService) {
        this.familyRepository = familyRepository;
        this.taxRepository = taxRepository;
        this.articleFamilyRepository = articleFamilyRepository;
        this.auditLogService = auditLogService;
    }

    @BasicLog
    @Override
    public Page<ArticleFamily> list(Long productTypeId,
        Long articleId, Integer status, Pageable pageable) {
        log.trace("Process started on service to list article families by articleId {} "
            + "and productTypeId {} and status {}", articleId, productTypeId, status);

        return articleFamilyRepository.findPageByProductTypeIdAndArticleIdAndStatus(
            productTypeId, articleId, status, pageable);
    }

    @BasicLog
    @Override
    public List<ArticleTaxDetail> findDetail(Long articleId, Long productType) {
        List<ArticleFamily> articleFamily = articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(productType, articleId,
                ACTIVE_STATUS);

        List<Long> familyIds = articleFamily.stream()
            .map(article -> article.getFamily().getId())
            .distinct()
            .collect(Collectors.toList());

        List<Tax> listTaxes = taxRepository
            .findByFamilyIdInAndStatusAndDeletedAtIsNull(familyIds, ACTIVE_STATUS);

        return listTaxes.stream()
            .map(tax -> mapArticleDetail(tax, articleId, productType))
            .collect(Collectors.toList());
    }

    @BasicLog
    @Override
    public ArticleFamily create(ArticleFamily articleFamily)
        throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process in service to create article family with object: {}.",
            articleFamily);

        performValidations(articleFamily);

        log.trace("Saving article family on DB. {}", articleFamily);
        ArticleFamily createdArticleFamily = articleFamilyRepository.save(articleFamily);
        log.trace("Article family saved successfully on DB. {}", createdArticleFamily);

        log.trace("Saving creation audit log");
        auditLogService.createAuditLog(createdArticleFamily);
        log.trace("Creation audit log saved successfully");

        return createdArticleFamily;
    }

    @BasicLog
    @Override
    public void delete(Long id) throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process on service delete article family with ID {}.", id);

        log.trace("Checking if article family with ID {} exist.", id);
        ArticleFamily articleFamily = findById(id);
        log.trace("Article family ID {} found: {}.", id, articleFamily);

        log.trace("Deleting article family with ID {}.", id);
        articleFamilyRepository.delete(articleFamily);
        log.trace("Article family with ID {} deleted successfully.", id);

        log.trace("Generating delete auditLog");
        auditLogService.deleteAuditLog(articleFamily);
        log.trace("AuditLog generated successfully");

    }

    private void performValidations(ArticleFamily articleFamily) throws BusinessRuleException {

        log.trace("Checking if family with id {} exists", articleFamily.getFamily().getId());
        findFamilyById(articleFamily.getFamily().getId());
        log.trace("Family with id {} found", articleFamily.getFamily().getId());

        log.trace("Checking if already exists article family with familyId {} and productTypeId {} "
                + "and articleId {}", articleFamily.getFamily().getId(),
            articleFamily.getProductTypeId(), articleFamily.getArticleId());
        boolean existsByFamilyIdAndProductTypeIdAndArticleId = articleFamilyRepository
            .existsByFamilyIdAndProductTypeIdAndArticleIdAndDeletedAtIsNull(
                articleFamily.getFamily().getId(),
                articleFamily.getProductTypeId(), articleFamily.getArticleId());

        if (existsByFamilyIdAndProductTypeIdAndArticleId) {
            BusinessRuleException ex =
                new BusinessRuleException("Already exists an article "
                    + "family with the same familyId and productTypeId and articleId");
            log.error(ex.getMessage(), ex);
            throw ex;
        }

    }

    private Family findFamilyById(Long id) {
        return familyRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Family with id %d not found.", id)));
    }

    private ArticleFamily findById(Long id) {
        return articleFamilyRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Article family with ID %1$d not found.", id)));
    }

    private ArticleTaxDetail mapArticleDetail(Tax taxes, Long article, Long productType) {
        return ArticleTaxDetail.builder()
            .productTypeId(productType)
            .articleId(article)
            .familyTaxTypeId(taxes.getTaxType().getId())
            .value(taxes.getValue().doubleValue())
            .dicTaxId(taxes.getId())
            .build();
    }
}
