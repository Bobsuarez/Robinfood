package com.robinfood.localserver.electronicbillbc.usescases;

import static com.robinfood.localserver.commons.constants.SefazConstants.ROUNDING_SCALE;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants.EMPTY_STRING;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants.TAG_NAME_EMIT;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants.TAG_NAME_IDE;

import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.FiscalElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.InfAdicDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.InfCfeDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.PgtoDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import com.robinfood.localserver.commons.exceptions.IncompleteDataException;
import com.robinfood.localserver.electronicbillbc.repositories.IFiscalElectronicCouponRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class SendFiscalElectronicCouponUseCase implements ISendFiscalElectronicCouponUseCase {

    private IDetailFiscalElectronicCouponUseCase detailFiscalElectronicCouponUseCase;

    private IEntityFiscalElectronicCouponUseCase entityFiscalElectronicCouponUseCase;

    private IPaymentMethodFiscalElectronicCouponUseCase paymentMethodFiscalElectronicCouponUseCase;

    private IFiscalElectronicCouponRepository fiscalElectronicCouponRepository;

    public SendFiscalElectronicCouponUseCase(
            IDetailFiscalElectronicCouponUseCase detailFiscalElectronicCouponUseCase,
            IEntityFiscalElectronicCouponUseCase entityFiscalElectronicCouponUseCase,
            IPaymentMethodFiscalElectronicCouponUseCase paymentMethodFiscalElectronicCouponUseCase,
            IFiscalElectronicCouponRepository fiscalElectronicCouponRepository
    ) {
        this.detailFiscalElectronicCouponUseCase = detailFiscalElectronicCouponUseCase;
        this.entityFiscalElectronicCouponUseCase = entityFiscalElectronicCouponUseCase;
        this.paymentMethodFiscalElectronicCouponUseCase = paymentMethodFiscalElectronicCouponUseCase;
        this.fiscalElectronicCouponRepository = fiscalElectronicCouponRepository;
    }

    @SneakyThrows
    @Override
    public SatHubResultDto invoke(OrderBillingDTO orderBillingDTO) {

        log.debug("Init SendFiscalElectronicCouponUseCase {}", orderBillingDTO);

        if (CollectionUtils.isEmpty(orderBillingDTO.getTreasuryPayments())) {

            String message = "TreasuryPayments of order: " + orderBillingDTO.getId() + " is empty o null";

            log.error(message);

            throw new IncompleteDataException(message);
        }

        Map<String, String> emitData = entityFiscalElectronicCouponUseCase.invoke(
                orderBillingDTO.getTreasuryEntities(), TAG_NAME_EMIT);

        Map<String, String> ideData = entityFiscalElectronicCouponUseCase.invoke(
                orderBillingDTO.getTreasuryEntities(), TAG_NAME_IDE);

        List<Map<String, String>> paymentMethods = paymentMethodFiscalElectronicCouponUseCase.invoke(
                orderBillingDTO.getTreasuryPayments(), orderBillingDTO.getDeduction(),
                orderBillingDTO.getTotal().setScale(ROUNDING_SCALE));

        BigDecimal totalDeductionAndDiscountOrder = orderBillingDTO.getDiscount().add(orderBillingDTO.getDeduction());

        List<DetDTO> listDet = detailFiscalElectronicCouponUseCase.invoke(orderBillingDTO.getProducts(),
                totalDeductionAndDiscountOrder);

        InfCfeDTO infCfeDTO = InfCfeDTO.builder()
                .emit(emitData)
                .ide(ideData)
                .det(listDet)
                .total(null)
                .pgto(PgtoDTO.builder().MP(paymentMethods).build())
                .infAdic(InfAdicDTO.builder().infCpl(EMPTY_STRING).build())
                .build();

        FiscalElectronicCouponDTO fiscalElectronicCouponDTO = FiscalElectronicCouponDTO.builder()
                .infCFe(infCfeDTO)
                .build();

        return fiscalElectronicCouponRepository.sendFiscalElectronicCoupon(
                fiscalElectronicCouponDTO,
                orderBillingDTO.getId()
        );
    }
}
