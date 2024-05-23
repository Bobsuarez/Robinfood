package com.robinfood.ordereports_bc_muyapp.usecases.getorderfinalproducts;

import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.OrderProductTaxDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseFinalProductDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseFinalProductGroupDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseTaxesDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductEntity;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductPortionEntity;
import com.robinfood.ordereports_bc_muyapp.models.mapper.OrderDetailOrderMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import com.robinfood.ordereports_bc_muyapp.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyfinalproductids.IGetOrderDiscountByFinalProductIdsUseCase;
import com.robinfood.ordereports_bc_muyapp.usecases.getorderproducttaxesbyfinalproductid.IGetOrderProductTaxesByFinalProductIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Component
public class GetOrderFinalProductsUseCase implements IGetOrderFinalProductsUseCase {

    private final IOrderFinalProductPortionRepository orderFinalProductPortionRepository;
    private final IOrderFinalProductRepository orderFinalProductRepository;
    private final IGetOrderProductTaxesByFinalProductIdUseCase getOrderProductTaxesByFinalProductIdUseCase;
    private final IGetOrderDiscountByFinalProductIdsUseCase getOrderDiscountByFinalProductIdsUseCase;
    private final OrderDetailOrderMapper orderDetailOrderMapper;

    @Override
    public ResponseOrderDetailDTO invoke(ResponseOrderDetailDTO responseOrderDetailDTO, Integer orderId) {

        CompletableFuture<List<OrderFinalProductEntity>> finalProductsFuture =
                orderFinalProductRepository.findAllByOrderId(orderId);

        CompletableFuture<List<OrderProductTaxDTO>> orderProductTaxFuture = finalProductsFuture.thenCompose(
                orderFinalProductEntities ->
                        getOrderProductTaxesByFinalProductIdUseCase.invoke(
                                orderFinalProductEntities.stream()
                                        .map(OrderFinalProductEntity::getId)
                                        .toList())
        );

        CompletableFuture<List<OrderDiscountDTO>> orderDiscountFinalProductFuture = finalProductsFuture.thenCompose(
                orderFinalProductEntities ->
                        getOrderDiscountByFinalProductIdsUseCase.invoke(
                                orderFinalProductEntities.stream()
                                        .map(OrderFinalProductEntity::getId)
                                        .toList())
        );

        CompletableFuture<List<OrderFinalProductPortionEntity>> orderProductPortionFuture =
                finalProductsFuture.thenCompose(
                        orderFinalProductEntities ->
                                orderFinalProductPortionRepository.findByOrderFinalProductIdIn(
                                        orderFinalProductEntities.stream()
                                                .map(OrderFinalProductEntity::getId)
                                                .toList())
                );

        CompletableFuture.allOf(
                        finalProductsFuture,
                        orderProductPortionFuture,
                        orderProductTaxFuture,
                        orderDiscountFinalProductFuture
                )
                .join();

        List<OrderFinalProductEntity> finalProducts = finalProductsFuture.join();
        List<OrderProductTaxDTO> orderProductTaxDTOS = orderProductTaxFuture.join();
        List<OrderFinalProductPortionEntity> orderFinalProductPortion = orderProductPortionFuture.join();
        List<OrderDiscountDTO> orderFinalProductDiscount = orderDiscountFinalProductFuture.join();

        return builderOrderFinalProducts(
                responseOrderDetailDTO,
                finalProducts,
                orderProductTaxDTOS,
                orderFinalProductPortion,
                orderFinalProductDiscount
        );
    }

    private ResponseOrderDetailDTO builderOrderFinalProducts(
            ResponseOrderDetailDTO responseOrderDetailDTO,
            List<OrderFinalProductEntity> finalProducts,
            List<OrderProductTaxDTO> orderProductTaxDTOS,
            List<OrderFinalProductPortionEntity> orderFinalProductPortion,
            List<OrderDiscountDTO> orderFinalProductDiscount
    ) {
        return responseOrderDetailDTO.toBuilder()
                .brands(finalProducts.stream()
                                .map(orderDetailOrderMapper::getBrandFinalProduct)
                                .distinct()
                                .toList())
                .products(finalProducts.stream()
                                  .map(orderDetailOrderMapper::mapFinalProductToResponseDTO)
                                  .map(discount -> discount.toBuilder()
                                          .discounts(getDiscountResponseDTO(
                                                  discount,
                                                  orderFinalProductDiscount
                                          ))
                                          .build())
                                  .map(taxes -> taxes.toBuilder()
                                          .taxes(getTaxesResponseDTO(taxes, orderProductTaxDTOS))
                                          .build())
                                  .map(product -> product.toBuilder()
                                          .groups(getProductPortionResponseDTO(product, orderFinalProductPortion))
                                          .build())
                                  .toList())
                .build();
    }

    private List<ResponseTaxesDTO> getTaxesResponseDTO(
            ResponseFinalProductDTO finalProduct,
            List<OrderProductTaxDTO> orderProductTaxDTOS
    ) {

        List<OrderProductTaxDTO> nuevo = orderProductTaxDTOS.stream()
                .filter(data -> data.getOrderFinalProductId()
                        .equals(finalProduct.getId()))
                .toList();
        return orderDetailOrderMapper.mapTaxesDTOToResponseDTO(nuevo);
    }

    private List<ResponseOrderDiscountDTO> getDiscountResponseDTO(
            ResponseFinalProductDTO finalProductDTO,
            List<OrderDiscountDTO> orderDiscountDTOS
    ) {

        List<OrderDiscountDTO> nuevo = orderDiscountDTOS.stream()
                .filter(data -> data.getOrderFinalProductId()
                        .equals(finalProductDTO.getId()))
                .toList();
        return orderDetailOrderMapper.mapFinalProductToResponseDiscountDTO(nuevo);
    }

    private List<ResponseFinalProductGroupDTO> getProductPortionResponseDTO(
            ResponseFinalProductDTO finalProduct,
            List<OrderFinalProductPortionEntity> productPortionEntities
    ) {

        List<OrderFinalProductPortionEntity> nuevo = productPortionEntities.stream()
                .filter(data -> data.getOrderFinalProductId()
                        .equals(finalProduct.getId()))
                .toList();
        return orderDetailOrderMapper.mapFinalProductGroupsToResponseDTO(nuevo);
    }
}
