package com.robinfood.app.datamocks.queue;

import com.robinfood.core.dtos.queue.BrandQueueDTO;
import com.robinfood.core.dtos.queue.OrderCreatedQueueDTO;
import com.robinfood.core.dtos.queue.ProductDTO;
import com.robinfood.core.dtos.queue.SizeQueueDTO;
import com.robinfood.core.dtos.queue.StoreDTO;
import com.robinfood.core.dtos.queue.UserDTO;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class OrderCreatedQueueDTOMock {

    private StoreDTO storeDTO = new StoreDTO(
        1L,
        "Store test"
    );

    private UserDTO userDTO = new UserDTO(
        "test@test.com",
        "User",
        1L,
        "Test",
        "57",
        "9999999999"
    );

    private SizeQueueDTO sizeQueueDTO = SizeQueueDTO.builder()
        .id(1L)
        .name("Size name test")
        .build();

    private BrandQueueDTO brandQueueDTO = BrandQueueDTO.builder()
        .id(1L)
        .sgiId(1L)
        .name("Brand name test")
        .build();

    private List<ProductDTO> productDTOS = Collections.singletonList(
        new ProductDTO(
            1L,
            1L,
            "image.png",
            "Product",
            sizeQueueDTO,
            brandQueueDTO,
            BigDecimal.valueOf(3000.0),
            BigDecimal.valueOf(7900.0),
            1
        )
    );

    public OrderCreatedQueueDTO orderCreated = OrderCreatedQueueDTO.builder()
        .id(1L)
        .orderDate("2020-01-01")
        .flowId(34L)
        .products(productDTOS)
        .paid(false)
        .store(storeDTO)
        .user(userDTO)
        .transactionId(1L)
        .build();
}
