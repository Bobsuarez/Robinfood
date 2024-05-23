package com.robinfood.app.usecases.createorderfinalproduct;

import com.robinfood.app.enums.CountryEnum;
import com.robinfood.app.mappers.FinalProductDeductionsMapper;
import com.robinfood.app.mappers.request.FinalProductPortionsMappers;
import com.robinfood.app.usecases.calculatesummationfinalproductaddition.ICalculateFinalProductAdditionUseCase;
import com.robinfood.app.usecases.calculatesummationfinalproductdiscount.ICalculateFinalProductDiscountUseCase;
import com.robinfood.app.usecases.calculatesummationfinalproducttax.ICalculateSummationFinalProductTaxUseCase;
import com.robinfood.app.usecases.createorderfinalproductportion.ICreateOrderFinalProductPortionsUseCase;
import com.robinfood.app.usecases.createorderproducttaxes.ICreateOrderProductTaxesUseCase;
import com.robinfood.app.usecases.createorderremovedportion.ICreateOrderRemovedPortionUseCase;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.PortionProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import com.robinfood.core.dtos.request.order.FinalProductRemovedPortionDTO;
import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import com.robinfood.core.dtos.request.order.OriginalReplacementPortionDTO;
import com.robinfood.core.dtos.request.order.RequestOrderPortionProductDTO;
import com.robinfood.core.entities.OrderDeductionFinalProductEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import com.robinfood.repository.orderproductodeduction.IOrderProductFinalDeductionRepository;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.robinfood.app.mappers.input.InputFinalProductMappers.toOrderFinalProductEntity;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of ICreateOrderFinalProductUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class CreateOrderFinalProductUseCase implements ICreateOrderFinalProductUseCase {

    private final ICalculateFinalProductDiscountUseCase calculateSummationFinalProductDiscountUseCase;
    private final ICalculateSummationFinalProductTaxUseCase calculateSummationFinalProductTaxUseCase;
    private final ICalculateFinalProductAdditionUseCase calculateSummationFinalProductAdditionUseCase;
    private final ICreateOrderFinalProductPortionsUseCase createOrderFinalProductPortionUseCase;
    private final ICreateOrderRemovedPortionUseCase createOrderRemovedPortionUseCase;
    private final IOrderFinalProductRepository orderFinalProductDataSource;
    private final ICreateOrderProductTaxesUseCase createOrderProductTaxesUseCase;
    private final IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;

    @Override
    public CompletableFuture<Boolean> invoke(
            List<FinalProductDTO> finalProductsDTO,
            Long orderId,
            Long companyId,
            Long storeId,
            Boolean paid
    ) {
        log.info(
                "Starting process to save final products has started with final products: {}, " +
                        "with order id: [{}], company id: [{}] and store id: [{}]",
                objectToJson(finalProductsDTO), orderId, companyId, storeId);

        final List<OrderFinalProductEntity> orderFinalProducts =
                buildOrderFinalProductsEntities(finalProductsDTO, orderId, companyId);

        final List<OrderFinalProductEntity> orderFinalProductEntities = CollectionsKt.toList(
                orderFinalProductDataSource.saveAll(orderFinalProducts)
        );

        final List<FinalProductPortionDTO> updatedPortionsDTO = new ArrayList<>();
        final List<FinalProductRemovedPortionDTO> updatedRemovedPortionsDTO = new ArrayList<>();

        List<CompletableFuture<Boolean>> completableFutureList = new ArrayList<>();

        for (int i = 0; i < finalProductsDTO.size(); i++) {
            final FinalProductDTO finalProductDTO = finalProductsDTO.get(i);
            final OrderFinalProductEntity productEntity = orderFinalProductEntities.get(i);

            List<FinalProductPortionDTO> portionsDTO = finalProductDTO.getPortions();
            List<FinalProductRemovedPortionDTO> removedPortionsDto = finalProductDTO.getRemovedPortions();

            OrderFinalProductEntity orderFinalProductEntity = orderFinalProductEntities.get(i);

            if (Objects.nonNull(finalProductDTO.getDeduction())) {

                List<OrderDeductionFinalProductEntity> listProduct =
                        FinalProductDeductionsMapper.listDeductionDtoToListOrderDeductionProductEntities(
                                finalProductDTO.getDeduction(),
                                orderId, productEntity.getId());

                orderProductFinalDeductionRepository.saveAll(listProduct);

            }

            for (FinalProductPortionDTO portionDTO : portionsDTO) {

                /*
                    If the portion have a replacement portion, we put his values
                    into order_final_product_portions to save
                 */
                if (portionDTO.getReplacementPortion() != null) {
                    buildReplacementPortionFinal(portionDTO);
                }

                portionDTO.setOrderId(orderFinalProductEntity.getOrderId());
                portionDTO.setOrderFinalProductId(orderFinalProductEntity.getId());
                portionDTO.setStoreId(storeId);
                portionDTO.setCompanyId(orderFinalProductEntity.getCompanyId());

                effectiveSale(paid, portionDTO);

            }

            removedPortionsDto.forEach((FinalProductRemovedPortionDTO removedPortionDTO) -> {
                removedPortionDTO.setOrderId(orderFinalProductEntity.getOrderId());
                removedPortionDTO.setFinalProductId(orderFinalProductEntity.getFinalProductId());
                removedPortionDTO.setOrderFinalProductId(orderFinalProductEntity.getId());
            });

            updatedPortionsDTO.addAll(portionsDTO);
            updatedRemovedPortionsDTO.addAll(removedPortionsDto);

            final List<FinalProductTaxDTO> finalProductTaxDTO = CollectionsKt.map(
                    finalProductDTO.getTaxes(),
                    (FinalProductTaxDTO finalProductTax) -> {
                        finalProductTax.setOrderFinalProductId(productEntity.getId());
                        finalProductTax.setOrderId(orderId);
                        return finalProductTax;
                    });

            completableFutureList.add(createOrderProductTaxesUseCase.invoke(finalProductTaxDTO));
        }

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).join();

        CompletableFuture.allOf(
                createOrderFinalProductPortionUseCase.invoke(updatedPortionsDTO),
                createOrderRemovedPortionUseCase.invoke(updatedRemovedPortionsDTO)
        ).join();

        return CompletableFuture.completedFuture(true);
    }

    private List<OrderFinalProductEntity> buildOrderFinalProductsEntities(
            List<FinalProductDTO> finalProductsDTO,
            Long orderId,
            Long companyId
    ) {
        return finalProductsDTO.stream()
                .map((FinalProductDTO finalProduct) -> {
                    finalProduct.setOrderId(orderId);

                    return buildOrderFinalProductEntity(
                            finalProduct,
                            orderId,
                            companyId
                    );
                })
                .collect(Collectors.toList());
    }

    private OrderFinalProductEntity buildOrderFinalProductEntity(
            FinalProductDTO finalProduct,
            Long orderId,
            Long companyId
    ) {
        OrderFinalProductEntity orderFinalProductEntity = toOrderFinalProductEntity(finalProduct);
        orderFinalProductEntity.setFinalProductName(finalProduct.getName());
        orderFinalProductEntity.setOrderId(orderId);
        orderFinalProductEntity.setCompanyId(companyId);
        orderFinalProductEntity.setSku(finalProduct.getSku());
        setDiscounts(finalProduct, orderFinalProductEntity);
        setTaxes(finalProduct, orderFinalProductEntity);
        calculateAndSetPrices(finalProduct, orderFinalProductEntity, companyId);

        return orderFinalProductEntity;
    }

    private void calculateAndSetPrices(
            FinalProductDTO finalProduct,
            OrderFinalProductEntity orderFinalProductEntity,
            Long companyId
    ) {
        log.info(
                "Starting process to calculate final totals to final product: {}, with product information: {}",
                objectToJson(finalProduct), objectToJson(orderFinalProductEntity));

        if (finalProduct.getPortions() != null && !finalProduct.getPortions().isEmpty()) {
            orderFinalProductEntity.setProductsPrice(
                    calculateSummationFinalProductAdditionUseCase.invoke(finalProduct)
            );
        }

        final BigDecimal productPrice = (
                orderFinalProductEntity.getBasePrice().multiply(
                        BigDecimal.valueOf(orderFinalProductEntity.getQuantity())
                )
        ).add(BigDecimal.valueOf(orderFinalProductEntity.getProductsPrice()));

        CountryEnum country = CountryEnum.findCountry(companyId);

        Map<String, Double> values = new HashMap<>();

        values.put(GlobalConstants.TOTAL_DISCOUNT, orderFinalProductEntity.getDiscountPrice());
        values.put(GlobalConstants.PRODUCT_PRICE, productPrice.doubleValue());
        values.put(GlobalConstants.TOTAL_TAXES, orderFinalProductEntity.getTotalTaxPrice());

        Double total = country.calculetotal(values);

        Double subTotal = country.calculeSubtotal(values);

        orderFinalProductEntity.setCo2Total(
                Objects.requireNonNullElse((finalProduct.getCo2Total()), BigDecimal.ZERO)
        );

        setCO2TotalMultiplyBasePriceByQuantity(finalProduct, orderFinalProductEntity);

        orderFinalProductEntity.setTotal(total);
        orderFinalProductEntity.setTotalPriceNt(subTotal);
    }

    private void setCO2TotalMultiplyBasePriceByQuantity(
            FinalProductDTO finalProduct,
            OrderFinalProductEntity orderFinalProductEntity
    ) {
        final BigDecimal quantityIntegerToBigDecimal = BigDecimal.valueOf(finalProduct.getQuantity());

        orderFinalProductEntity.setCo2CompensationPriceTotal(
                Objects.requireNonNullElse((finalProduct.getCo2Total().multiply(quantityIntegerToBigDecimal)),
                        BigDecimal.ZERO));
    }

    private void setTaxes(
            FinalProductDTO finalProductDTO,
            OrderFinalProductEntity orderFinalProductEntity
    ) {
        if (Objects.nonNull(finalProductDTO.getTaxes()) && !finalProductDTO.getTaxes().isEmpty()) {
            orderFinalProductEntity.setTotalTaxPrice(
                    calculateSummationFinalProductTaxUseCase.invoke(finalProductDTO)
            );
        }
    }

    private void setDiscounts(
            FinalProductDTO finalProductDTO,
            OrderFinalProductEntity orderFinalProductEntity
    ) {
        if (Objects.nonNull(finalProductDTO.getDiscounts())) {
            orderFinalProductEntity.setDiscountPrice(
                    calculateSummationFinalProductDiscountUseCase.invoke(finalProductDTO)
            );
        }
    }

    private void buildReplacementPortionFinal(FinalProductPortionDTO finalProductPortionDTO) {

        // Mapper original portion
        final PortionProductDTO originalProduct =
                FinalProductPortionsMappers.toPortionProductDTO(finalProductPortionDTO);

        final OriginalReplacementPortionDTO originalReplacementPortionDTO =
                FinalProductPortionsMappers.toOriginalReplacementPortionDTO(
                        finalProductPortionDTO,
                        originalProduct
                );

        finalProductPortionDTO.getReplacementPortion().setOriginalReplacementPortionDTO(originalReplacementPortionDTO);

        // Mapper replacement portion
        final RequestOrderPortionProductDTO product =
                new RequestOrderPortionProductDTO(

                        finalProductPortionDTO.getReplacementPortion().getProduct().getId(),
                        finalProductPortionDTO.getReplacementPortion().getProduct().getName()
                );

        finalProductPortionDTO.setId(finalProductPortionDTO.getReplacementPortion().getId());
        finalProductPortionDTO.setName(finalProductPortionDTO.getReplacementPortion().getName());
        finalProductPortionDTO.setSku(finalProductPortionDTO.getReplacementPortion().getSku());
        finalProductPortionDTO.setProduct(product);
        finalProductPortionDTO.setUnitId(finalProductPortionDTO.getReplacementPortion().getUnitId());
        finalProductPortionDTO.setUnitNumber(finalProductPortionDTO.getReplacementPortion().getUnitNumber());
    }

    private void effectiveSale(Boolean paid, FinalProductPortionDTO portion) {

        if (Boolean.TRUE.equals(paid)) {
            portion.setEffectiveSale(DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT);
            return;
        }

        portion.setEffectiveSale(DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT);
    }
}
