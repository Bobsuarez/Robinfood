package com.robinfood.app.usecases.gettaxescriteria;

import com.robinfood.app.usecases.getstoreinfo.IGetStoreInfoUseCase;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.models.domain.configuration.FlowTax;
import com.robinfood.core.models.domain.configuration.State;
import com.robinfood.core.models.domain.configuration.StoreInformation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class GetTaxesCriteriaTest {

        @InjectMocks
        private GetTaxCriteriaUseCase getTaxCriteriaUseCase;

        @Mock
        private IGetStoreInfoUseCase getStoreInfoUseCase;

        final String token = "token";

        @Test
        void test_Get_Tax_Criteria() {
                FlowTax flowtax = FlowTax.builder().value(2L).build();
                StoreDTO store = new StoreDTO();
                Map<String, Object> criteria = new HashMap<String,Object>();
                criteria.put("location_id", 1L);
                criteria.put("flow_id", 1L);
                store.setId(1L);

                OrderDTO order = OrderDTO.builder().id(1L).store(store).taxCriteria(criteria).build();
                State state = State.builder().id(1L).build();
                StoreInformation storeInfo = StoreInformation.builder().flowTax(flowtax).state(state).build();
                when(getStoreInfoUseCase.invoke(token,1L))
                        .thenReturn(storeInfo);
                getTaxCriteriaUseCase.invoke(token, order);

                verify(getStoreInfoUseCase, times(1)).invoke(token,1L);

        }


        @Test
        void test_Get_Tax_Criteria_Null() {

                StoreDTO store = new StoreDTO();
                Map<String, Object> criteria = new HashMap<String,Object>();
                criteria.put("location_id", 1L);
                criteria.put("flow_id", 1L);
                store.setId(1L);

                OrderDTO order = OrderDTO.builder().id(1L).store(store).taxCriteria(criteria).build();
                State state = State.builder().id(1L).build();
                StoreInformation storeInfo = StoreInformation.builder().flowTax(new FlowTax()).state(state).build();
                when(getStoreInfoUseCase.invoke(token,1L))
                        .thenReturn(storeInfo);
                getTaxCriteriaUseCase.invoke(token, order);

                verify(getStoreInfoUseCase, times(1)).invoke(token,1L);


        }
}
