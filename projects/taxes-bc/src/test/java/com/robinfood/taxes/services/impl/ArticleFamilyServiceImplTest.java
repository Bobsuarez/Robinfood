package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.domain.ArticleTaxDetail;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.AbstractBaseEntity;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.ArticleFamily;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.repositories.ArticleFamilyRepository;
import com.robinfood.taxes.repositories.FamilyRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.services.AuditLogService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ArticleFamilyServiceImplTest {

    @InjectMocks
    private ArticleFamilyServiceImpl articleFamilyService;

    @Mock
    private ArticleFamilyRepository articleFamilyRepository;

    @Mock
    private TaxRepository taxRepository;

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private AuditLogService auditLogService;

    @Test
    void test_List_Should_RetrieveArticleFamiliesSuccessfully_When_ReceiveParams() {

        Page<ArticleFamily> articleFamilies = new PageImpl<>(
            Collections.singletonList(getArticleFamily()));

        Pageable pageable = PageRequest.of(0, 10);

        when(articleFamilyRepository.findPageByProductTypeIdAndArticleIdAndStatus(
            anyLong(), anyLong(), anyInt(), any(Pageable.class))).thenReturn(articleFamilies);

        assertAll(() -> articleFamilyService
            .list(1L, 1L, 1, pageable));

        verify(articleFamilyRepository, times(1))
            .findPageByProductTypeIdAndArticleIdAndStatus(anyLong(), anyLong(), anyInt(),
                any(Pageable.class));

    }

    @Test
    void test_List_Should_RetrieveArticleFamiliesSuccessfully_When_ReceiveNoParams() {

        Page<ArticleFamily> articleFamilies = new PageImpl<>(
            Collections.singletonList(getArticleFamily()));

        Pageable pageable = PageRequest.of(0, 10);

        when(articleFamilyRepository.findPageByProductTypeIdAndArticleIdAndStatus(
            eq(null), eq(null), eq(null), any(Pageable.class))).thenReturn(articleFamilies);

        assertAll(() -> articleFamilyService
            .list(null, null, null, pageable));

        verify(articleFamilyRepository, times(1))
            .findPageByProductTypeIdAndArticleIdAndStatus(eq(null), eq(null), eq(null),
                any(Pageable.class));

    }

    @Test
    void test_ListDetail_Should_RetrieveArticleTaxDetailSuccessfully_When_ReceiveParams() {

        List<ArticleFamily> articleFamilies = List.of(getArticleFamily());
        List<Tax> listTaxes = List.of(getTax());

        when(articleFamilyRepository
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt())).thenReturn(articleFamilies);
        when(taxRepository.findByFamilyIdInAndStatusAndDeletedAtIsNull(anyList(), anyInt()))
            .thenReturn(listTaxes);

        assertAll(() -> articleFamilyService.findDetail(1L, 1L));

        verify(articleFamilyRepository, times(1))
            .findByProductTypeIdAndArticleIdAndStatusAndDeletedAtIsNull(anyLong(), anyLong(),
                anyInt());
        verify(taxRepository, times(1))
            .findByFamilyIdInAndStatusAndDeletedAtIsNull(anyList(), anyInt());

    }

    @Test
    void test_Create_Should_ThrowEntityNotFoundException_When_FamilyIdNotFound()
        throws JsonProcessingException {

        when(familyRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
            () -> articleFamilyService.create(getArticleFamily()));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(articleFamilyRepository, times(0))
            .existsByFamilyIdAndProductTypeIdAndArticleIdAndDeletedAtIsNull(anyLong(), anyLong(),
                anyLong());
        verify(articleFamilyRepository, times(0)).save(any(ArticleFamily.class));
        verify(auditLogService, times(0)).createAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Create_Should_ThrowBusinessRuleException_When_AlreadyExistingRelationship()
        throws JsonProcessingException {

        when(familyRepository.findById(anyLong())).thenReturn(Optional.of(mock(Family.class)));
        when(articleFamilyRepository.existsByFamilyIdAndProductTypeIdAndArticleIdAndDeletedAtIsNull(
            anyLong(), anyLong(), anyLong())).thenReturn(true);

        assertThrows(BusinessRuleException.class,
            () -> articleFamilyService.create(getArticleFamily()));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(articleFamilyRepository, times(1))
            .existsByFamilyIdAndProductTypeIdAndArticleIdAndDeletedAtIsNull(anyLong(), anyLong(),
                anyLong());
        verify(articleFamilyRepository, times(0)).save(any(ArticleFamily.class));
        verify(auditLogService, times(0)).createAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Create_Should_CreateArticleFamilySuccessfully_When_ReceiveValidInput()
        throws JsonProcessingException {

        when(familyRepository.findById(anyLong())).thenReturn(Optional.of(mock(Family.class)));
        when(articleFamilyRepository.existsByFamilyIdAndProductTypeIdAndArticleIdAndDeletedAtIsNull(
            anyLong(), anyLong(), anyLong())).thenReturn(false);
        when(articleFamilyRepository.save(any(ArticleFamily.class))).thenReturn(getArticleFamily());

        assertAll(() -> articleFamilyService.create(getArticleFamily()));

        verify(familyRepository, times(1)).findById(anyLong());
        verify(articleFamilyRepository, times(1))
            .existsByFamilyIdAndProductTypeIdAndArticleIdAndDeletedAtIsNull(anyLong(), anyLong(),
                anyLong());
        verify(articleFamilyRepository, times(1)).save(any(ArticleFamily.class));
        verify(auditLogService, times(1)).createAuditLog(any(AbstractBaseEntity.class));

    }

    @Test
    void test_Delete_Should_DeleteArticleFamily_When_ArticleFamilyExists()
        throws JsonProcessingException {
        when(articleFamilyRepository.findById(anyLong()))
            .thenReturn(Optional.of(getArticleFamily()));
        doNothing().when(articleFamilyRepository).delete(any(ArticleFamily.class));
        doNothing().when(auditLogService).deleteAuditLog(any(ArticleFamily.class));

        assertAll(() -> articleFamilyService.delete(1L));

        verify(articleFamilyRepository, times(1)).findById(anyLong());
        verify(articleFamilyRepository, times(1)).delete(any(ArticleFamily.class));
        verify(auditLogService, times(1)).deleteAuditLog(any(ArticleFamily.class));
    }

    @Test
    void test_Delete_Should_ThrowEntityNotFoundException_When_ArticleFamilyDoesNotExist()
        throws JsonProcessingException {
        when(articleFamilyRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> articleFamilyService.delete(1L));

        verify(articleFamilyRepository, times(1)).findById(anyLong());
        verify(articleFamilyRepository, times(0)).delete(any(ArticleFamily.class));
        verify(auditLogService, times(0)).createAuditLog(any(ArticleFamily.class));
    }

    private ArticleFamily getArticleFamily() {
        return ArticleFamily.builder()
            .articleId(1L)
            .family(new Family(1L))
            .productTypeId(1L)
            .build();
    }

    private Tax getTax() {
        return Tax.builder()
            .value(BigDecimal.valueOf(10))
            .name("test")
            .description("test")
            .applyRules(true)
            .sapId("123")
            .status(1)
            .taxType(new TaxType(1L))
            .build();
    }

}
