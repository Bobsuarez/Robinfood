package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.repositories.RuleTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RuleTypeServiceImplTest {

    @InjectMocks
    private RuleTypeServiceImpl ruleTypeService;

    @Mock
    private RuleTypeRepository ruleTypeRepository;

    @Test
    void test_findAll_Should_ListFamilyTypes_When_ReceiveValidData() {

        List<RuleType> ruleTypesList = new ArrayList<>();
        RuleType ruleType = new RuleType(1l);
        ruleType.setName("test");
        ruleTypesList.add(ruleType);

        when(ruleTypeRepository.findAll()).thenReturn(ruleTypesList);

        assertAll(() -> ruleTypeService.findAll());

        verify(ruleTypeRepository, times(1)).findAll();
    }
}
