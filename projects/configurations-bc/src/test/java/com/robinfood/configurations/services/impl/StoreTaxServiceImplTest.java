package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.models.StoreTax;
import com.robinfood.configurations.repositories.jpa.StoreTaxRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreTaxServiceImplTest {

    @InjectMocks
    private StoreTaxServiceImpl storeTaxService;

    @Mock
    private StoreTaxRepository storeTaxRepository;

    @Test
    void test_FindStoreById_Should_ReturnStoreTax() {

        when(storeTaxRepository.findByStoresIdIs(1L)).thenReturn(List.of(new StoreTax()));
        List<StoreTax> result = storeTaxService.findByIdStore(1L);
        assertEquals(result, List.of(new StoreTax()));
    }

}
