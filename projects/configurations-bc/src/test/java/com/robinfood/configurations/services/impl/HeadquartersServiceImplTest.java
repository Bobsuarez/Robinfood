package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.repositories.jpa.HeadquartersRepository;
import com.robinfood.configurations.samples.HeadquarterSample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HeadquartersServiceImplTest {

    @Mock
    private HeadquartersRepository headquartersRepository;

    @InjectMocks
    private HeadquartersServiceImpl service;

    @Test
    void testFindByCompanyCountryId() {
        when(
            headquartersRepository.findByCompanyId(1L)
        ).thenReturn(Optional.of(HeadquarterSample.getHeadquarter()));

        assertAll(() ->
            service.getByCompanyCountryId(1L));
    }

    @Test
    void testFindByCompanyCountryId_fail() {
        when(
            headquartersRepository.findByCompanyId(1L)
        ).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
            service.getByCompanyCountryId(1L));
    }
}
