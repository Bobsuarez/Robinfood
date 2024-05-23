package com.robinfood.app.usecases.configuretaxinfo;

import com.robinfood.app.mocks.TransactionRequestDTOMock;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConfigureTaxInfoUseCaseTest {

    @InjectMocks
    private ConfigureTaxInfoUseCase configureTaxInfoUseCase;

    private TransactionRequestDTO transactionRequestDTOMocks;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(configureTaxInfoUseCase, "col", BigDecimal.valueOf(8));
        ReflectionTestUtils.setField(configureTaxInfoUseCase, "mex", BigDecimal.valueOf(16));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5","1","2"})
    void test_configure_tax_info(String country) {

        transactionRequestDTOMocks = new TransactionRequestDTOMock().transactionRequestDTOWithOutServices;

        transactionRequestDTOMocks.getCompany().setId(Long.valueOf(country));
        configureTaxInfoUseCase.invoke(transactionRequestDTOMocks);
        if ("5".equals(country)) {
            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotal(),
                    BigDecimal.valueOf(7900.0000).setScale(4));

            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotalDiscount(),
                    BigDecimal.valueOf(3000.0));
        } else {
            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotal(),
                    BigDecimal.valueOf(7900.0000).setScale(4));

            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotalDiscount(),
                    BigDecimal.valueOf(3000.0));
        }
    }

    @Test
    void test_configure_tax_info_exception() {

        transactionRequestDTOMocks = new TransactionRequestDTOMock().transactionRequestDTO;
        transactionRequestDTOMocks.getCompany().setId(Long.valueOf("8"));
        assertThrows(TransactionCreationException.class, () -> {
            configureTaxInfoUseCase.invoke(transactionRequestDTOMocks);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"5","1","2"})
    void Given_Services_Empty_Then_Do_Not_Sum_Services(String country) {

        transactionRequestDTOMocks = new TransactionRequestDTOMock().transactionRequestWithoutServicesDTO;

        transactionRequestDTOMocks.getCompany().setId(Long.valueOf(country));
        configureTaxInfoUseCase.invoke(transactionRequestDTOMocks);
            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotal(),
                    BigDecimal.valueOf(7900.00).setScale(4));

            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotalDiscount(),
                    BigDecimal.valueOf(3000.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5","1","2"})
    void test_configure_tax_info_with_services(String country) {

        transactionRequestDTOMocks = new TransactionRequestDTOMock().transactionRequestDTO;

        transactionRequestDTOMocks.getCompany().setId(Long.valueOf(country));
        configureTaxInfoUseCase.invoke(transactionRequestDTOMocks);
        if ("5".equals(country)) {
            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotal(),
                    BigDecimal.valueOf(7900.0000).setScale(4));

            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotalDiscount(),
                    BigDecimal.valueOf(5000.0));
        } else {
            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotal(),
                    BigDecimal.valueOf(7900.0000).setScale(4));

            assertEquals(transactionRequestDTOMocks.getOrders().get(0).getTotalDiscount(),
                    BigDecimal.valueOf(5000.0));
        }
    }

}
