package com.robinfood.util;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.dtos.sendordertosimba.request.OrderDTO;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.exceptions.DataNotFoundException;
import com.robinfood.exceptions.FieldsValidateOrRequiredException;
import com.robinfood.mappers.ResponseMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.constants.GeneralConstants.ORDERS;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_DATA_NOT_FOUND_COMPLEMENT;

public class ValidatorFieldsUtil {

    private ValidatorFieldsUtil() {
        throw new IllegalStateException("Utility class");
    }

    @SneakyThrows
    public static void validateNullFields(Object entity, String... fieldList) {

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            List<String> fieldsToValidate = Arrays.asList(fieldList);
            if (!fieldsToValidate.contains(field.getName())) {
                continue;
            }
            if (field.get(entity) == null) {
                String errorMessage = field.getName() + " is required";
                throw new FieldsValidateOrRequiredException(
                        ResponseMapper.buildWithError(
                                HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                errorMessage,
                                Boolean.TRUE
                        ), errorMessage
                );
            }
            validateType(field, entity);
        }
    }

    @SneakyThrows
    private static void validateType(Field field, Object entity) {

        var getNameField = field.getName();
        var getValueField = field.get(entity);
        var getTypeField = field.getType();

        if (getTypeField == Integer.class) {
            ValidateTypeFieldUtil.isInteger(getValueField, getNameField);
        }

        if (getTypeField == String.class) {
            ValidateTypeFieldUtil.isEmpty((String) getValueField, getNameField);
        }

        if (getNameField.equals("documentNumber")) {
            ValidateTypeFieldUtil.isInteger(getValueField, getNameField);
        }

        if (getNameField.equals("documentType")) {
            ValidateTypeFieldUtil.isWithinRange(getNameField, (Integer) getValueField, 1, 4);
        }

        if (getNameField.equals("email")) {
            ValidateTypeFieldUtil.isEmail((String) getValueField, getNameField);
        }
    }


    @SneakyThrows
    public static void validateNullUnityFields(Object valueField, String nameField) {

        if (Objects.isNull(valueField)) {

            throw new FieldsValidateOrRequiredException(
                    ResponseMapper
                            .buildWithError(HttpStatusConstants.SC_PRECONDITION_FAILED.getCodeHttp(),
                                    nameField + " is required",
                                    Boolean.TRUE
                            ), nameField + " is required");

        }
    }

    @SneakyThrows
    public static OrderDTO validateOrderDTO(TransactionRequestDTO transactionRequestDTO) {


        List<OrderDTO> listOrderDTO = Optional.ofNullable(transactionRequestDTO.getOrders())
                .orElseThrow(() -> new DataNotFoundException(
                        ResponseMapper
                                .buildWithError(HttpStatusConstants.SC_NOT_FOUND.getCodeHttp(),
                                        ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(ORDERS),
                                        DEFAULT_BOOLEAN_TRUE),
                        ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(ORDERS))
                );

        if (listOrderDTO.isEmpty()) {
            executeDataNotFoundException();
        }

        OrderDTO orderDTO = listOrderDTO.get(0);

        ValidatorFieldsUtil.validateNullUnityFields(orderDTO.getId(), "order_id");

        return orderDTO;
    }

    private static void executeDataNotFoundException() {
        throw new DataNotFoundException(
                ResponseMapper
                        .buildWithError(HttpStatusConstants.SC_NOT_FOUND.getCodeHttp(),
                                ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(ORDERS),
                                DEFAULT_BOOLEAN_TRUE),
                ERROR_DATA_NOT_FOUND_COMPLEMENT.replaceComplement(ORDERS));
    }
}
