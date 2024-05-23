package com.robinfood.app.usecases.distributeplatformdeliveryfee;

import com.robinfood.app.usecases.getallactivetypedeductions.IGetTotalDeductionsUseCase;
import com.robinfood.app.util.ServicesUtil;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Component
@Slf4j
public class DistributedPlatformDeliveryFeeUseCase implements IDistributedPlatformDeliveryFeeUseCase {

    private final IGetTotalDeductionsUseCase getTotalDeductionsUseCase;

    public DistributedPlatformDeliveryFeeUseCase(IGetTotalDeductionsUseCase getTotalDeductionsUseCase) {
        this.getTotalDeductionsUseCase = getTotalDeductionsUseCase;
    }

    @Override
    public void invoke(TransactionRequestDTO transactionRequestDTO, String token) {

        BigDecimal totals = getTotalDeductionsUseCase.invoke(transactionRequestDTO, token);

        if (BigDecimal.ZERO.compareTo(totals) == GlobalConstants.DEFAULT_INTEGER_VALUE) {
            return;
        }

        List<DeductionDTO> listDeductionsOrder = transactionRequestDTO
                .getOrders()
                .stream()
                .map(orde -> distributed(orde, totals, orde.getTotal()))
                .collect(Collectors.toList());

        List<DeductionDTO> listRoundOrders = roundOrder(listDeductionsOrder, totals);

        transactionRequestDTO.setOrders(assingDeduction(listRoundOrders, transactionRequestDTO));

        log.info("Distributing deductions in transaction: {}, with values: orders {}",
                objectToJson(transactionRequestDTO.getDeductions()), objectToJson(transactionRequestDTO.getOrders()));

    }

    private List<DeductionDTO> roundOrder(List<DeductionDTO> listDeductions, BigDecimal totalDeduction) {

        BigDecimal totalRealDeductions = listDeductions
                .stream()
                .map(DeductionDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal discountsDifference = totalDeduction.subtract(totalRealDeductions);

        final DeductionDTO lastDedudctionOfFirstOrder = listDeductions
                .get(listDeductions.size() - 1);

        listDeductions.get(listDeductions.size() - 1).
                setValue(lastDedudctionOfFirstOrder.getValue().add(discountsDifference));

        return listDeductions;

    }

    private List<OrderDTO> assingDeduction(
            List<DeductionDTO> deductions,
            TransactionRequestDTO transactionRequestDTO
    ) {

        for (int index = 0; index < transactionRequestDTO.getOrders().size(); index++) {
            transactionRequestDTO.getOrders().get(index).getDeductions().add(deductions.get(index));
            int finalIndex = index;
            List<DeductionDTO> listDeductionProduct = transactionRequestDTO.getOrders()
                    .get(index)
                    .getFinalProducts()
                    .stream()
                    .map(product ->
                            distributedProduct(
                                    product, deductions.get(finalIndex).getValue(),
                                    transactionRequestDTO.getOrders().get(finalIndex).getTotal()))
                    .collect(Collectors.toList());

            List<DeductionDTO> listDeductionProductRounded = roundOrder(
                    listDeductionProduct, deductions.get(finalIndex).getValue());

            transactionRequestDTO.getOrders().get(index)
                    .setFinalProducts(assingDeductionProduct(
                            listDeductionProductRounded,
                            transactionRequestDTO.getOrders().get(index).getFinalProducts()));
        }

        return transactionRequestDTO.getOrders();
    }

    private List<FinalProductDTO> assingDeductionProduct(
            List<DeductionDTO> deductions, List<FinalProductDTO> listProduct
    ) {

        for (int index = 0; index < listProduct.size(); index++) {

            listProduct.get(index).getDeduction().add(deductions.get(index));

        }
        return listProduct;
    }

    private DeductionDTO distributed(OrderDTO order, BigDecimal valueDeduction, BigDecimal valueTotal) {

        final BigDecimal totalService = ServicesUtil.getNetValueByOrderDto(order);

        final BigDecimal calculateDiscount = order.getTotal().subtract(totalService)
                .multiply(valueDeduction)
                .divide(valueTotal, GlobalConstants.TAX_ROUNDING, RoundingMode.FLOOR);

        return new DeductionDTO(GlobalConstants.DEDUCTION_DEFAULT, calculateDiscount);
    }

    private DeductionDTO distributedProduct(
            FinalProductDTO product,
            BigDecimal valueDeduction, BigDecimal valueTotal
    ) {

        final BigDecimal calculateDiscount = product.getTotalDiscounts()
                .multiply(valueDeduction)
                .divide(valueTotal, GlobalConstants.TAX_ROUNDING, RoundingMode.FLOOR);

        return new DeductionDTO(GlobalConstants.DEDUCTION_DEFAULT, calculateDiscount);
    }
}
