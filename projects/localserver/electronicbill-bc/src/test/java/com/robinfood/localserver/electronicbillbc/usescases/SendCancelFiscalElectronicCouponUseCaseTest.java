package com.robinfood.localserver.electronicbillbc.usescases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.CancelElectronicCouponDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.cancelsale.InfCfeCancelDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.sathub.response.CFe;
import com.robinfood.localserver.commons.dtos.sathub.response.EmitResponse;
import com.robinfood.localserver.commons.dtos.sathub.response.Ide;
import com.robinfood.localserver.commons.dtos.sathub.response.InfCFe;
import com.robinfood.localserver.commons.dtos.sathub.response.SatCFE;
import com.robinfood.localserver.commons.exceptions.IncompleteDataException;
import com.robinfood.localserver.electronicbillbc.mocks.dto.SatCFEMock;
import com.robinfood.localserver.electronicbillbc.repositories.IFiscalElectronicCouponRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendCancelFiscalElectronicCouponUseCaseTest {

    @InjectMocks
    private SendCancelSaleElectronicCouponUseCase sendCancelSaleElectronicCouponUseCase;

    @Mock
    private IFiscalElectronicCouponRepository fiscalElectronicCouponRepository;

    private final SatCFE satCFE = new SatCFEMock().satCFE;


    @Test
    void test_Send_Cancel_Success_Fiscal_Electronic_Coupon_With_Discount() throws JsonProcessingException {

        InfCFe infCFe = InfCFe.builder()
                .emit(EmitResponse.builder()
                        .CNPJ("12345")
                        .build())
                .Id("1")
                .ide(Ide.builder()
                        .CNPJ("12345")
                        .numeroCaixa("123")
                        .build())
                .build();

        CFe cfe = CFe.builder()
                .infCFe(infCFe)
                .build();

        SatCFE satCFEResponse = SatCFE.builder().CFe(cfe).build();

        HashMap<String, String> ideMap = new HashMap<>();
        ideMap.put("cnpj", satCFEResponse.getCFe().getInfCFe().getIde().getCNPJ());
        ideMap.put("numeroCaixa", satCFEResponse.getCFe().getInfCFe().getIde().getNumeroCaixa());

        CancelElectronicCouponDTO cancelElectronicCouponDTO = CancelElectronicCouponDTO
                .builder()
                .infCFe(InfCfeCancelDTO
                        .builder()
                        .det(null)
                        .pgto(null)
                        .total(null)
                        .ide(ideMap)
                        .chCanc("1")
                        .build())
                .build();

        when(fiscalElectronicCouponRepository.sendCancelElectronicCoupon(cancelElectronicCouponDTO, 1L, "12345"))
                .thenReturn(SatHubResultDto.builder()
                        .cccc("12345")
                        .CNPJ("98765")
                        .build());

        SatHubResultDto satHubResultDto = sendCancelSaleElectronicCouponUseCase.invoke(satCFEResponse, 1L);

        Assertions.assertNotNull(satHubResultDto);
    }

    @Test
    void test_Send_Cancel_Empty_Information_Fiscal_Electronic_Coupon() {

        SatCFE satCFEEmpty = new SatCFE();

        IncompleteDataException responseException = Assertions.assertThrows(IncompleteDataException.class, () ->
                sendCancelSaleElectronicCouponUseCase.invoke(satCFEEmpty, 1L)
        );

        Assertions.assertEquals("This is no sefaz response", responseException.getMessage());
    }

    @Test
    void test_Send_Cancel_Null_Fiscal_Electronic_Coupon() {

        Assertions.assertThrows(NullPointerException.class, () ->
                sendCancelSaleElectronicCouponUseCase.invoke(null,1L)
        );
    }
}
