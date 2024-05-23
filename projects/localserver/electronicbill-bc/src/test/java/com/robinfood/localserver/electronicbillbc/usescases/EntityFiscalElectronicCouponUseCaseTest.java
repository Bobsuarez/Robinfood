package com.robinfood.localserver.electronicbillbc.usescases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTaxDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.EntityTreasuryDepartmentDTO;
import com.robinfood.localserver.electronicbillbc.mocks.dto.TreasuryEntitiesDTOMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@ExtendWith(MockitoExtension.class)
class EntityFiscalElectronicCouponUseCaseTest {

    @InjectMocks
    private EntityFiscalElectronicCouponUseCase entityFiscalElectronicCouponUseCase;

    @Test
    void test_Create_Entity_When_Object_Content() throws JsonProcessingException {

        final TreasuryEntitiesDTOMock treasuryEntitieDTOMock = new TreasuryEntitiesDTOMock();
        final List<EntityTreasuryDepartmentDTO> treasuryEntitieDTOMockList = new ArrayList<>();

        treasuryEntitieDTOMockList.add(treasuryEntitieDTOMock.getDefaultEntityDTOIdeData());

        final Map<String, String> ideData = entityFiscalElectronicCouponUseCase.invoke(
                treasuryEntitieDTOMockList,"ide");

        final Map<String, String> ideDataMock = new TreeMap<>();
        ideDataMock.put("xNome", "xNome");
        ideDataMock.put("numeroCaixa", "002");
        ideDataMock.put("signAC", "TEST");
        ideDataMock.put("CNPJ", "35880842000160");

        Assertions.assertEquals(ideDataMock,ideData);
    }

    @Test
    void test_Create_Entity_When_Object_Empty() {

        final List<EntityTreasuryDepartmentDTO> treasuryEntitiesDTOMockList = new ArrayList<>();

        Assertions.assertThrows(NullPointerException.class, () ->
            entityFiscalElectronicCouponUseCase.invoke(treasuryEntitiesDTOMockList,"ide")
        );
    }

    @Test
    void test_Create_Entity_When_Object_Null() {

        Assertions.assertThrows(NullPointerException.class, () ->
            entityFiscalElectronicCouponUseCase.invoke(null,"ide")
        );
    }
}
