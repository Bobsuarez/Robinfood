package com.robinfood.app.usecases.getlistpaymentmethods;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.core.dtos.PaymentMethodEntityDTO;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;

@Component
public class GetListPaymentMethodsUseCase implements IGetListPaymentMethodsUseCase {

    private final IPaymentMethodRepository paymentMethodRepository;

    private final ObjectMapper objectMapper;

    public GetListPaymentMethodsUseCase(IPaymentMethodRepository paymentMethodRepository, ObjectMapper objectMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PaymentMethodEntityDTO> invoke() {

        final List<PaymentMethodEntity> paymentMethodEntities = paymentMethodRepository
                .findByStatusId(DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT);

        return paymentMethodEntities
                .stream()
                .map(paymentMethodEntity ->
                        objectMapper.convertValue(paymentMethodEntity, PaymentMethodEntityDTO.class))
                .collect(Collectors.toList());
    }
}
