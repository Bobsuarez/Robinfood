package com.robinfood.storeor.repositories.electronicbillingrepository;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingRequestEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingResponseEntity;
import com.robinfood.storeor.mappers.IElectronicBillingMapper;
import com.robinfood.storeor.mocks.dto.electronicbilling.CreateElectronicBillingRequestDTOMocks;
import com.robinfood.storeor.mocks.dto.electronicbilling.CreateElectronicBillingResponseDTOMocks;
import com.robinfood.storeor.mocks.dto.electronicbilling.ElectronicBillDTOMock;
import com.robinfood.storeor.mocks.entity.electronicbilling.ElectronicBillingEntityMock;
import com.robinfood.storeor.mocks.entity.electronicbilling.ElectronicBillingRequestEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElectronicBillingRepositoryTest {

    final String token = "token";

    final CreateElectronicBillingResponseDTO createElectronicBillingResponseDTOMock =
            new CreateElectronicBillingResponseDTOMocks().createElectronicBillingResponseDTO;

    final CreateElectronicBillingRequestDTO createElectronicBillingRequestDTOMock =
            new CreateElectronicBillingRequestDTOMocks().createElectronicBillingRequestDTO;

    final ElectronicBillingRequestEntity electronicBillingRequestEntity =
            new ElectronicBillingRequestEntityMock().electronicBillingRequestEntity;

    @InjectMocks
    private ElectronicBillingRepository electronicBillingRepository;

    @Mock
    private IElectronicBillingRemoteDataSource electronicBillingRemoteDataSource;

    @Mock
    private IElectronicBillingMapper electronicBillingMapper;

    final ElectronicBillingResponseEntity electronicBillingResponseEntity = new
            ElectronicBillingResponseEntity("2022-04-06T03:18:52.678",
            "3d4fd4a0-dc7d-47c6-91c7-a5b6dc385370",
            "Register created electronic billing"
    );

    final APIResponseEntity<ElectronicBillingResponseEntity> apiResponseEntity = new APIResponseEntity<>(
            200,
            electronicBillingResponseEntity,
            "2022-04-06T03:18:53.817292Z",
            "Save electronic billing successfully",
            "OK"
    );

    @Test
    void test_When_Save_Electronic_Billing_Order() {

        when(electronicBillingMapper.createElectronicBillingRequestDTOToElectronicBillingRequestEntity
                (createElectronicBillingRequestDTOMock)
        ).thenReturn(electronicBillingRequestEntity);

        when(electronicBillingMapper.electronicBillingResponseEntityToCreateElectronicBillingResponseDTO
                (apiResponseEntity.getData())
        ).thenReturn(createElectronicBillingResponseDTOMock);

        when(electronicBillingRemoteDataSource.save(token, electronicBillingRequestEntity))
                .thenReturn(apiResponseEntity);

        when(electronicBillingRepository.save(token, createElectronicBillingRequestDTOMock))
                .thenReturn(createElectronicBillingResponseDTOMock);

        CreateElectronicBillingResponseDTO createElectronicBillingResponseDTOTest = electronicBillingRepository
                .save(token, createElectronicBillingRequestDTOMock);

        assertEquals(createElectronicBillingResponseDTOTest, createElectronicBillingResponseDTOMock);

    }

    @Test
    void test_When_findAllByOrderIdIn_Succes() {

        List<Long> ordersId=new ArrayList<>();
        ordersId.add(1l);
        ordersId.add(2l);

        ElectronicBillingEntityMock electronicBillingEntityMock= new  ElectronicBillingEntityMock();

        ElectronicBillDTOMock electronicBillDTOMock= new ElectronicBillDTOMock();

        when(electronicBillingRemoteDataSource.findAllByOrderIdIn(token,ordersId ))
                .thenReturn(new APIResponseEntity<>(
                        200,
                        electronicBillingEntityMock.getDataListDefault(),
                        "2022-04-06T03:18:53.817292Z",
                        "successful search process",
                        "OK"
                ));

        when(electronicBillingMapper.electronicBillingEntityListToElectronicBillDTOList(
                electronicBillingEntityMock.getDataListDefault()
                ))
                .thenReturn(electronicBillDTOMock.getDefaultListData());


        List<ElectronicBillDTO> listElectronicBillDto =
                electronicBillingRepository.findAllByOrderIdIn(token,ordersId);

        assertEquals(electronicBillDTOMock.getDefaultListData(),listElectronicBillDto);

    }


}
