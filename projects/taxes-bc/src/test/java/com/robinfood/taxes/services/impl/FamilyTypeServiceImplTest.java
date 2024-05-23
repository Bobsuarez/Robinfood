package com.robinfood.taxes.services.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.repositories.FamilyTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FamilyTypeServiceImplTest {

    @InjectMocks
    private FamilyTypeServiceImpl familyTypeService;

    @Mock
    private FamilyTypeRepository familyTypeRepository;

    @Test
    void test_findAll_Should_ListFamilyTypes_When_ReceiveValidData() {

        List<FamilyType> familyTypesList = new ArrayList<>();
        FamilyType familyType = new FamilyType(1l);
        familyType.setName("test");
        familyTypesList.add(familyType);

        when(familyTypeRepository.findAll()).thenReturn(familyTypesList);

        assertAll(() -> familyTypeService.findAll());

        verify(familyTypeRepository, times(1)).findAll();
    }
}
