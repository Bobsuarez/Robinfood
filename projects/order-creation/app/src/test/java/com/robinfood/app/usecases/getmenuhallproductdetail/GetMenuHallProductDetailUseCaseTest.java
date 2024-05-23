package com.robinfood.app.usecases.getmenuhallproductdetail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.app.mocks.MenuHallProductDetailDTOMocks;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.menuhallproductdetail.MenuHallProductDetailDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.menu.IMenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class GetMenuHallProductDetailUseCaseTest {

    @Mock
    private IMenuRepository mockMenuRepository;

    @InjectMocks
    private GetMenuHallProductDetailUseCase getMenuHallProductDetailUseCase;

    private final TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

    final String token = "token";

    @Test
    void test_Get_Menu_Hall_Products_Detail_Change_Request_Throw_TransactionCreationException() {
        when(mockMenuRepository.getMenuHallProductDetail(
                eq(token),
                anyLong()
        )).thenThrow(new TransactionCreationException("Some exception",
                "Error getting menu hall product detail", TransactionCreationErrors.MENU_HALL_PRODUCT_ID,
                HttpStatus.PRECONDITION_FAILED));

        try {
            getMenuHallProductDetailUseCase.invoke(token, transactionRequest).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains("Error getting menu hall product detail"));
        }
    }

    @Test
    void test_Get_Menu_Hall_Products_Detail_Change_Request_Throw_Exception() {
        when(mockMenuRepository.getMenuHallProductDetail(
                eq(token),
                anyLong()
        )).thenThrow(new RuntimeException("Some exception"));

        try {
            getMenuHallProductDetailUseCase.invoke(token, transactionRequest).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains("Some exception"));
        }
    }

    @Test
    void test_Get_Menu_Hall_Products_Detail_Change_Request_Info() throws JsonProcessingException {

        MenuHallProductDetailDTO responseMock = MenuHallProductDetailDTOMocks.aMenuHallProductDTO();

        when(mockMenuRepository.getMenuHallProductDetail(
                token,
                1L
        )).thenReturn(
                responseMock
        );

        final TransactionCreationResult result = getMenuHallProductDetailUseCase.invoke(token, transactionRequest)
                .join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
        assertEquals(responseMock.getName(), transactionRequest.getOrders().get(0).getFinalProducts().get(0).getName());
        assertEquals(responseMock.getSizeName(),
                transactionRequest.getOrders().get(0).getFinalProducts().get(0).getSize().getName());
    }
}
