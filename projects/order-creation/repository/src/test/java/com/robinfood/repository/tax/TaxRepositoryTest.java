package com.robinfood.repository.tax;

import com.robinfood.core.dtos.ValidateTaxItemRequestDTO;
import com.robinfood.core.dtos.ValidateTaxItemResponseDTO;
import com.robinfood.core.dtos.ValidateTaxRequestDTO;
import com.robinfood.core.dtos.ValidateTaxResponseDTO;
import com.robinfood.core.entities.ValidateTaxItemRequestEntity;
import com.robinfood.core.entities.ValidateTaxItemResponseEntity;
import com.robinfood.core.entities.ValidateTaxRequestEntity;
import com.robinfood.core.entities.ValidateTaxResponseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxRepositoryTest {

    @Mock
    private ITaxRemoteDataSource mockTaxRemoteDataSource;

    @InjectMocks
    private TaxRepository taxRepository;

    private final String token = "token";

    private final ValidateTaxRequestEntity validateTaxRequestEntity = new ValidateTaxRequestEntity(
            new HashMap<>(),
            Arrays.asList(
                    new ValidateTaxItemRequestEntity(
                            1L,
                            1L,
                            BigDecimal.valueOf(30),
                            BigDecimal.valueOf(74.3),
                            4
                    ),
                    new ValidateTaxItemRequestEntity(
                            2L,
                            2L,
                            BigDecimal.valueOf(0),
                            BigDecimal.valueOf(30.3),
                            1
                    )
            )
    );
    private final List<ValidateTaxResponseEntity> validateTaxResponseEntities = Collections.singletonList(
            new ValidateTaxResponseEntity(
                    3L,
                    1L,
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(14.9),
                    2,
                    Arrays.asList(
                            new ValidateTaxItemResponseEntity(
                                    1L,
                                    3L,
                                    "PIS 1,65%",
                                    BigDecimal.valueOf(0.0165),
                                    "gjmv",
                                    1L,
                                    1L,
                                    BigDecimal.valueOf(0.225)
                            ),
                            new ValidateTaxItemResponseEntity(
                                    1L,
                                    7L,
                                    "COFINS 7,6%",
                                    BigDecimal.valueOf(0.076),
                                    "gjmv",
                                    1L,
                                    1L,
                                    BigDecimal.valueOf(1.0365)
                            )
                    ),
                    BigDecimal.valueOf(2.523)
            )
    );
    private final ValidateTaxRequestDTO validateTaxRequestDTO = new ValidateTaxRequestDTO(
            new HashMap<>(),
            Arrays.asList(
                    new ValidateTaxItemRequestDTO(
                            1L,
                            1L,
                            BigDecimal.valueOf(30),
                            BigDecimal.valueOf(74.3),
                            4
                    ),
                    new ValidateTaxItemRequestDTO(
                            2L,
                            2L,
                            BigDecimal.valueOf(0),
                            BigDecimal.valueOf(30.3),
                            1
                    )
            )
    );
    private final List<ValidateTaxResponseDTO> validateTaxResponseDTOS = Collections.singletonList(
            new ValidateTaxResponseDTO(
                    3L,
                    1L,
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(14.9),
                    2,
                    Arrays.asList(
                            new ValidateTaxItemResponseDTO(
                                    1L,
                                    3L,
                                    BigDecimal.valueOf(0.0165),
                                    "gjmv",
                                    1L,
                                    1L,
                                    "PIS 1,65%",
                                    BigDecimal.valueOf(0.225)
                            ),
                            new ValidateTaxItemResponseDTO(
                                    1L,
                                    7L,
                                    BigDecimal.valueOf(0.076),
                                    "gjmv",
                                    1L,
                                    1L,
                                    "COFINS 7,6%",
                                    BigDecimal.valueOf(1.0365)
                            )
                    ),
                    BigDecimal.valueOf(2.523)
            )
    );

    @Test
    void test_Get_Final_Product_Taxes_Returns_Successfully() {
        when(mockTaxRemoteDataSource.getFinalProductsTaxes(token, validateTaxRequestEntity))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseEntities));

        final List<ValidateTaxResponseDTO> result = taxRepository.getFinalProductsTaxes(token, validateTaxRequestDTO).join();

        assertEquals(validateTaxResponseDTOS, result);
    }

}
