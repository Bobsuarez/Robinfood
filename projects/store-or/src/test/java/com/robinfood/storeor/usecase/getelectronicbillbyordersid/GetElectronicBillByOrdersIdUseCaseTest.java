package com.robinfood.storeor.usecase.getelectronicbillbyordersid;


import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mocks.dto.electronicbilling.ElectronicBillDTOMock;
import com.robinfood.storeor.mocks.entity.electronicbilling.ElectronicBillingEntityMock;
import com.robinfood.storeor.repositories.electronicbillingrepository.IElectronicBillingRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GetElectronicBillByOrdersIdUseCaseTest {

    @Mock
    private IElectronicBillingRepository electronicBillingRepository;

    @Mock
    private  IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private  GetElectronicBillByOrdersIdUseCase getElectronicBillByOrdersIdUseCase;

    private final String token = "token";

    @Test
   void test_Get_electronic_bill_completed_ok(){

        ElectronicBillDTOMock mockElectronicBillDTOMock= new  ElectronicBillDTOMock();
        List<Long> ordersId=new ArrayList<>();
        ordersId.add(1l);
        ordersId.add(2l);

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );
        when(electronicBillingRepository.findAllByOrderIdIn(token,ordersId))
                .thenReturn(mockElectronicBillDTOMock.getDefaultListData());

        List<ElectronicBillDTO> response=getElectronicBillByOrdersIdUseCase.invoke(ordersId);

        assertEquals(response,mockElectronicBillDTOMock.getDefaultListData());
    }

    @Test
    void test_Get_electronic_bill_not_found(){

        ElectronicBillDTOMock mockElectronicBillDTOMock= new  ElectronicBillDTOMock();
        List<Long> ordersId=new ArrayList<>();
        ordersId.add(1l);
        ordersId.add(2l);

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(electronicBillingRepository.findAllByOrderIdIn(token,ordersId))
                .thenReturn(new ArrayList<>());

        List<ElectronicBillDTO> response=getElectronicBillByOrdersIdUseCase.invoke(ordersId);

        assertEquals(response,new ArrayList<>());

    }

    @Test
    void test_Get_electronic_bill_error(){
        List<Long> ordersId=new ArrayList<>();
        ordersId.add(1l);
        ordersId.add(2l);

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(electronicBillingRepository.findAllByOrderIdIn(token,ordersId))
                .thenThrow(new NotFoundException("NOT FOUND RESOUR"));

        Assertions.assertThrows(NotFoundException.class, () ->
                getElectronicBillByOrdersIdUseCase.invoke(ordersId));
    }
}
