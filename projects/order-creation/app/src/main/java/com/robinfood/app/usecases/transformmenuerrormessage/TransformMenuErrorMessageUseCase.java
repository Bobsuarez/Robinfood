package com.robinfood.app.usecases.transformmenuerrormessage;

import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.MenuDataSkuDTO;
import com.robinfood.core.dtos.MenuDiscountErrorDTO;
import com.robinfood.core.dtos.MenuValidationMessageErrorDTO;
import com.robinfood.core.dtos.MenuValidationMessageErrorDiscountDTO;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.util.ObjectMapperSingleton.jsonToClass;

@AllArgsConstructor
@Component
@Slf4j
public class TransformMenuErrorMessageUseCase implements ITransformMenuErrorMessageUseCase {

    @Override
    public void invoke(OrderToCreateDTO orderToCreateDTO, TransactionCreationException transactionCreationException) {

        String deliveryMessage = "";

        if (orderToCreateDTO.getErrorCode().equals(
                TransactionCreationErrors.INVALID_MENU.getErrorCode()
        )
        ) {

            MenuValidationMessageErrorDTO menuValidationMessageErrorDTO = jsonToClass(
                    (String) transactionCreationException.getData(),
                    MenuValidationMessageErrorDTO.class);

            deliveryMessage = createdMessage(
                    menuValidationMessageErrorDTO.getMessage(),
                    menuValidationMessageErrorDTO.getError().get(GlobalConstants.DEFAULT_INTEGER_VALUE),
                    menuValidationMessageErrorDTO.getData(),
                    menuValidationMessageErrorDTO.getRequest()
            );

            List<String> skuList = menuValidationMessageErrorDTO.getData().stream().map(
                    MenuDataSkuDTO::getSku).collect(Collectors.toList());

            orderToCreateDTO.setErrorMessage(deliveryMessage);
            orderToCreateDTO.setDescription(deliveryMessage);
            orderToCreateDTO.setSkuRejected(skuList);

        }

        if (
                orderToCreateDTO.getErrorCode().equals(
                        TransactionCreationErrors.INVALID_MENU_DISCOUNT.getErrorCode()
                )
        ) {

            MenuValidationMessageErrorDiscountDTO menuValidationMessageErrorDTO = jsonToClass(
                    (String) transactionCreationException.getData(),
                    MenuValidationMessageErrorDiscountDTO.class);

            List<String> reasonList = menuValidationMessageErrorDTO.getData()
                    .getDiscountErrors()
                    .stream()
                    .map(MenuDiscountErrorDTO::getReason)
                    .collect(Collectors.toList());

            deliveryMessage = createdMessage(
                    menuValidationMessageErrorDTO.getMessage(),
                    menuValidationMessageErrorDTO.getError(),
                    reasonList,
                    menuValidationMessageErrorDTO.getRequest()
            );
            orderToCreateDTO.setErrorMessage(deliveryMessage);
            orderToCreateDTO.setDescription(deliveryMessage);
        }
    }

    public static String createdMessage(String message, Object err, Object data, String request) {

        return String.format(
                "%s %s %s in service %s",
                message,
                err,
                data,
                request
        );
    }
}
