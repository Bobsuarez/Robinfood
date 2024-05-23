package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.InfCfeCancelDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.sathub.response.SatCFE;
import com.robinfood.localserver.commons.exceptions.IncompleteDataException;
import com.robinfood.localserver.electronicbillbc.repositories.IFiscalElectronicCouponRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@Slf4j
public class SendCancelSaleElectronicCouponUseCase implements ISendCancelSaleElectronicCouponUseCase {

    private IFiscalElectronicCouponRepository fiscalElectronicCouponRepository;

    public SendCancelSaleElectronicCouponUseCase(
            IFiscalElectronicCouponRepository fiscalElectronicCouponRepository
    ) {
        this.fiscalElectronicCouponRepository = fiscalElectronicCouponRepository;
    }

    @SneakyThrows
    @Override
    public SatHubResultDto invoke(SatCFE satCFE, Long orderId) {

        log.debug("Init SendCancelSaleElectronicCouponUseCase {}", satCFE);

        if (Objects.isNull(satCFE.getCFe())) {

            String message = "This is no sefaz response";

            log.error(message);

            throw new IncompleteDataException(message);
        }

        HashMap<String, String> ideMap = new HashMap<>();
        ideMap.put("cnpj", satCFE.getCFe().getInfCFe().getIde().getCNPJ());
        ideMap.put("numeroCaixa", satCFE.getCFe().getInfCFe().getIde().getNumeroCaixa());

        String emitCnpj = satCFE.getCFe().getInfCFe().getEmit().getCnpj();

        InfCfeCancelDTO infCfeCancelDTO = InfCfeCancelDTO
                .builder()
                .det(null)
                .pgto(null)
                .total(null)
                .ide(ideMap)
                .chCanc(satCFE.getCFe().getInfCFe().getId())
                .build();

        CancelElectronicCouponDTO cancelElectronicCouponDTO = CancelElectronicCouponDTO
                .builder()
                .infCFe(infCfeCancelDTO)
                .build();

        return fiscalElectronicCouponRepository.sendCancelElectronicCoupon(
                cancelElectronicCouponDTO,
                orderId,
                emitCnpj
        );
    }
}
