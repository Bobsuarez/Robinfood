package com.robinfood.app.usecases.getbrandidsbyorder;

import com.robinfood.app.datamocks.dto.input.OrderDTODataMock;
import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import kotlin.collections.CollectionsKt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetBrandIdsByOrderTest {

    @InjectMocks
    GetBrandIdsByOrder getBrandIdsByOrder;

    private final List<FinalProductDTO> finalProductDTOList = new OrderDTODataMock()
            .getDataDefault(4L)
            .getFinalProducts();

    final  List<BrandDTO> brandDTOList = CollectionsKt.map(finalProductDTOList, FinalProductDTO::getBrand);
    final List<Long> brandIds = CollectionsKt.map(brandDTOList, BrandDTO::getId);

    @Test
    void test_Get_BrandIds_By_Order(){
         final List<Long> result = getBrandIdsByOrder.invoke(finalProductDTOList);

        assertEquals(brandIds, result);
    }
}
