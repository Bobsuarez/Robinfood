package com.robinfood.app.usecases.transformmenuerrormessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.TransactionResponseDTOMock;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.MenuDiscountErrorDTO;
import com.robinfood.core.dtos.MenuValidationMessageErrorDTO;
import com.robinfood.core.dtos.MenuValidationMessageErrorDiscountDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.util.ObjectMapperSingleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransformMenuErrorMessageUseCaseTest {

    @InjectMocks
    private TransformMenuErrorMessageUseCase transformMenuErrorMessageUseCase;

    private final TransactionCreationResponseDTO transactionCreationResponseDTO = new TransactionResponseDTOMock().transactionCreationResponseDTO;
    private final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();
    private final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

    @Test
    void test_TransformMenuErrorMessageUseCase_when_product_price_difference_error() {

        OrderToCreateDTO orderToCreateDTO = new OrderToCreateDTO();
        orderToCreateDTO.setPaid(true);
        orderToCreateDTO.setErrorCode(2001);
        String jsonStringResponse = "{\"code\":404,\"message\":\"Validation of order failed.\",\"error\"" +
                ":[\"[Incoming product price does not match internal price]\"],\"data\":[{\"id\":716,\"sku\"" +
                ":\"12277230298940309599\",\"errorMessage\"" +
                ":\"Incoming product price (64.00) does not match internal price (29700.00)\"}]}";

        MenuValidationMessageErrorDTO menuValidationMessageErrorDTO = ObjectMapperSingleton.jsonToClass(
                jsonStringResponse,
                MenuValidationMessageErrorDTO.class);

        String responseMessage = String.format(
                "%s %s %s in service %s",
                menuValidationMessageErrorDTO.getMessage(),
                menuValidationMessageErrorDTO.getError().get(GlobalConstants.DEFAULT_INTEGER_VALUE),
                menuValidationMessageErrorDTO.getData(),
                menuValidationMessageErrorDTO.getRequest()
        );

        transformMenuErrorMessageUseCase.invoke(
                orderToCreateDTO,
                new TransactionCreationException(
                        jsonStringResponse, null,
                        TransactionCreationErrors.INVALID_MENU,
                        HttpStatus.BAD_REQUEST)
        );

        assertEquals(orderToCreateDTO.getErrorMessage(), responseMessage);

    }

    @Test
    void test_TransformMenuErrorMessageUseCase_when_discount_not_valid_error() {

        OrderToCreateDTO orderToCreateDTO = new OrderToCreateDTO();
        orderToCreateDTO.setPaid(true);
        orderToCreateDTO.setErrorCode(2003);
        String jsonErrorDiscount = "{\"code\":400" +
                ",\"message\":\"Validation of order failed.\"" +
                ",\"error\":null,\"data\":{\"discount_errors\"" +
                ":[{\"entity\":\"ARTICLE\",\"sourceId\":133,\"quantity\":2,\"value\":15500.0,\"valid\":false,\"reason\""
                +
                ":\"Validated discount 4000.0000 is greater than the configured discount 2000.0000. " +
                "Discount is not valid.\",\"discounts\":[{\"name\":\"Promo Gran Pecado iFood Co\"" +
                ",\"discountType\":\"Valor\",\"value\":2000.0,\"maxDiscount\":2000.0}]}]}" +
                ",\"request\": \"https://dev.menu-bc.muydev.com/api/v1/orders/validate\"}";

        MenuValidationMessageErrorDiscountDTO menuValidationMessageErrorDTO = ObjectMapperSingleton.jsonToClass(
                jsonErrorDiscount,
                MenuValidationMessageErrorDiscountDTO.class);

        List<String> lista = menuValidationMessageErrorDTO.getData()
                .getDiscountErrors()
                .stream()
                .map(MenuDiscountErrorDTO::getReason)
                .collect(Collectors.toList());

        String responseMessage = String.format(
                "%s %s %s in service %s",
                menuValidationMessageErrorDTO.getMessage(),
                menuValidationMessageErrorDTO.getError(),
                lista,
                menuValidationMessageErrorDTO.getRequest()
        );

        transformMenuErrorMessageUseCase.invoke(
                orderToCreateDTO,
                new TransactionCreationException(
                        jsonErrorDiscount, null,
                        TransactionCreationErrors.INVALID_MENU_DISCOUNT,
                        HttpStatus.BAD_REQUEST
                )
        );

        assertEquals(orderToCreateDTO.getErrorMessage(), responseMessage);

    }

}
