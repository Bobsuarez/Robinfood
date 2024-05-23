package com.robinfood.core.mappers;

import com.robinfood.core.dtos.menuhallproductdetail.MenuHallProductDetailDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public final class MenuHallProductDetailMapper {

    private MenuHallProductDetailMapper() {
    }

    public static void setMenuProductDetailInfo(@NotNull TransactionRequestDTO transactionRequest,
            Map<Long, MenuHallProductDetailDTO> menuHallProductsDetail) {
        transactionRequest.getOrders()
                .forEach(orderDTO -> orderDTO.getFinalProducts().forEach((FinalProductDTO finalProductDTO) -> {

                    MenuHallProductDetailDTO menuHallProductDetailDTO = menuHallProductsDetail.get(
                            finalProductDTO.getArticle().getMenuHallProductId());

                    finalProductDTO.getSize().setName(menuHallProductDetailDTO.getSizeName());
                    finalProductDTO.setName(menuHallProductDetailDTO.getName());
                }));
    }
}
