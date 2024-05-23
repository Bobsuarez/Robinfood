package com.robinfood.app.usecases.completeproducthalldata;

import com.robinfood.app.usecases.completereplacementportionsdata.ICompleteReplacementPortionsDataUseCase;
import com.robinfood.app.usecases.getmenuproducts.IGetMenuProductsUseCase;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.menu.MenuGroupPortionDTO;
import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.dtos.menu.MenuProductGroupDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of ICompleteMenuProductsDataFromMenuHallsUseCase
 */
@Slf4j
public class CompleteMenuProductsDataFromMenuHallsUseCase implements ICompleteMenuProductsDataFromMenuHallsUseCase {

    private final IGetMenuProductsUseCase getMenuProductsUseCase;
    private final ICompleteReplacementPortionsDataUseCase completeReplacementPortionsDataUseCase;

    public CompleteMenuProductsDataFromMenuHallsUseCase(IGetMenuProductsUseCase getMenuProductsUseCase,
            ICompleteReplacementPortionsDataUseCase completeReplacementPortionsDataUseCase) {
        this.getMenuProductsUseCase = getMenuProductsUseCase;
        this.completeReplacementPortionsDataUseCase = completeReplacementPortionsDataUseCase;
    }

    @Override
    public void invoke(
            Long countryId,
            Long flowId,
            List<OrderDetailProductDTO> orderDetailProducts,
            Long storeId,
            String token
    ) {

        log.info("Starting process to complete Menu Products from Menu Halls with products [{}]", orderDetailProducts);
        final List<OrderDetailProductGroupPortionDTO> portionsWithReplacement = new ArrayList<>();

        orderDetailProducts.forEach(
                (OrderDetailProductDTO orderDetailProduct) ->
                        replacePortionsInfo(countryId, flowId, storeId, token, portionsWithReplacement,
                                orderDetailProduct)

        );

        if (!portionsWithReplacement.isEmpty()) {
            completeReplacementPortionsDataUseCase.invoke(portionsWithReplacement, token);
        }
    }

    private void replacePortionsInfo(Long countryId, Long flowId, Long storeId, String token,
            List<OrderDetailProductGroupPortionDTO> portionsWithReplacement, OrderDetailProductDTO orderDetailProduct) {

        final List<MenuHallProductResponseDTO> productDetailsResponse = getMenuProductsUseCase.invoke(
                orderDetailProduct.getBrandMenuId(), countryId, flowId,
                Collections.singletonList(orderDetailProduct.getMenuHallProductId()), storeId, token
        );

        productDetailsResponse.stream()
                .filter(productDetailResponse -> orderDetailProduct.getMenuHallProductId()
                        .equals(productDetailResponse.getId())).findFirst()
                .ifPresent(productDetail -> setDataToProduct(productDetail, orderDetailProduct));

        portionsWithReplacement.addAll(this.getOrderDetailProductGroupPortionDTO(
                orderDetailProduct
        ));
    }

    private List<OrderDetailProductGroupPortionDTO> getOrderDetailProductGroupPortionDTO(
            OrderDetailProductDTO orderDetailProductDTO
    ) {

        final List<OrderDetailProductGroupPortionDTO> portionsWithReplacement = new ArrayList<>();

        orderDetailProductDTO.getGroups().forEach(orderDetailProductGroup ->
                portionsWithReplacement.addAll(orderDetailProductGroup.getPortions()
                        .stream()
                        .filter(OrderDetailProductGroupPortionDTO::hasReplacement)
                        .collect(Collectors.toList()))
        );

        return portionsWithReplacement;
    }

    /**
     * Sets data to product from product detail
     *
     * @param incomingProductDetail the product detail with complete data
     * @param orderDetailProduct    the product to set the missing data
     */
    private void setDataToProduct(
            MenuHallProductResponseDTO incomingProductDetail,
            OrderDetailProductDTO orderDetailProduct
    ) {

        // Sets image, article and sku data to product
        orderDetailProduct.setArticleId(incomingProductDetail.getArticleId());
        orderDetailProduct.setArticleName(incomingProductDetail.getTypeName());
        orderDetailProduct.setArticleTypeId(incomingProductDetail.getType());
        orderDetailProduct.setImage(incomingProductDetail.getImage());
        orderDetailProduct.setSku(incomingProductDetail.getSku());

        // Saves in [portionGroupsDetail] all the portions coming from menu service
        final List<MenuGroupPortionDTO> menuGroupPortionDTOS = incomingProductDetail.getGroups()
                .stream()
                .map(MenuProductGroupDTO::getPortions)
                .flatMap(Collection::stream).collect(Collectors.toList());

        // Set group sku and portions sku by iterating order detail product groups and comparing with
        // product detail coming from service
        if (Objects.nonNull(orderDetailProduct.getGroups())) {
            orderDetailProduct.getGroups().forEach(
                    (OrderDetailProductGroupDTO group) -> incomingProductDetail.getGroups().stream()
                            .filter(menuProductGroupDTO -> group.getId().equals(menuProductGroupDTO.getId()))
                            .findAny().ifPresent(
                                    (MenuProductGroupDTO groupDetail) ->
                                            replacePortion(menuGroupPortionDTOS, group, groupDetail)

                            )
            );
        }
    }

    private void replacePortion(List<MenuGroupPortionDTO> menuGroupPortionDTOS, OrderDetailProductGroupDTO group,
            MenuProductGroupDTO groupDetail) {
        
        group.setSku(groupDetail.getSku());
        group.getPortions().forEach(
                portion -> menuGroupPortionDTOS.stream()
                        .filter(menuGroupPortionDTO -> portion.getParentId()
                                .equals(menuGroupPortionDTO.getParentId())
                        ).findAny().ifPresent(
                                portionDTO -> portion.setSku(portionDTO.getSku())
                        )
        );
    }
}
