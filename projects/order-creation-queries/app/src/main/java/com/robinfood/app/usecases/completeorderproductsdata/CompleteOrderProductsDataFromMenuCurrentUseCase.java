package com.robinfood.app.usecases.completeorderproductsdata;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

import com.robinfood.app.usecases.getmenucurrent.IGetMenuCurrentUseCase;
import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.dtos.MenuProductDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of ICompleteOrderProductsDataFromMenuCurrentUseCase
 */
@Slf4j
public class CompleteOrderProductsDataFromMenuCurrentUseCase implements
        ICompleteOrderProductsDataFromMenuCurrentUseCase {

    private final IGetMenuCurrentUseCase getMenuCurrentUseCase;

    public CompleteOrderProductsDataFromMenuCurrentUseCase(IGetMenuCurrentUseCase getMenuCurrentUseCase) {
        this.getMenuCurrentUseCase = getMenuCurrentUseCase;
    }

    @Override
    public void invoke(
            Long countryId,
            Long flowId,
            List<OrderDetailProductDTO> orderDetailProductDTOList,
            Long storeId,
            String token
    ) {
        log.info(
                "Starting process to complete Order Products data  from current Menu with products [{}]",
                orderDetailProductDTOList
        );

        Long currentBrandId = 0L;
        MenuCurrentDTO menuCurrentDTO = new MenuCurrentDTO();

        for (OrderDetailProductDTO orderDetailProductDTO : orderDetailProductDTOList) {

            final Long brandId = orderDetailProductDTO.getBrandMenuId();

            if (!currentBrandId.equals(brandId)) {
                currentBrandId = brandId;

                menuCurrentDTO = getMenuCurrentUseCase.invoke(
                        brandId,
                        countryId,
                        flowId,
                        storeId,
                        token
                );
            }

            final List<MenuProductDTO> menuProductDTOList = new ArrayList<>();

            menuCurrentDTO.getHalls()
                    .forEach(menuHallsDTO -> menuProductDTOList.addAll(menuHallsDTO.getMenuProductDTOList()));

            final List<MenuProductDTO> menuProductDTOListFilter = menuProductDTOList
                    .stream()
                    .filter(menuProductDTO -> Objects.nonNull(menuProductDTO.getParentId())
                            && menuProductDTO.getParentId().equals(orderDetailProductDTO.getId()))
                    .filter(menuProductDTO -> menuProductDTO.getSizeId().equals(orderDetailProductDTO.getSizeId()))
                    .collect(Collectors.toList());

            if (menuProductDTOListFilter.isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found in menu with parentId: "
                                + orderDetailProductDTO.getId()
                                + " and sizeId: "
                                + orderDetailProductDTO.getSizeId()
                );
            }

            orderDetailProductDTO.setDisplayType(menuProductDTOListFilter.get(DEFAULT_INTEGER_VALUE).getDisplayType());
            orderDetailProductDTO.setMenuHallProductId(menuProductDTOListFilter.get(DEFAULT_INTEGER_VALUE).getId());
            orderDetailProductDTO.setArticleTypeId(menuProductDTOListFilter.get(DEFAULT_INTEGER_VALUE).getTypeId());
            orderDetailProductDTO.setSku(menuProductDTOListFilter.get(DEFAULT_INTEGER_VALUE).getSku());
        }
    }
}
