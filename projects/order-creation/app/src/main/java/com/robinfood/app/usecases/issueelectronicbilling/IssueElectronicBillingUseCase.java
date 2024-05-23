package com.robinfood.app.usecases.issueelectronicbilling;

import com.robinfood.app.usecases.getposresolution.IGetPosResolutionUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.logging.constants.CompanyEnum;
import com.robinfood.repository.electronicbill.IElectronicBillRepository;
import org.springframework.beans.factory.annotation.Value;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IssueElectronicBillingUseCase implements IIssueElectronicBillingUseCase {

    @Value("${orders.electronic-bill-store-ids}")
    List<Long> electronicBillStoreIds;

    private final IElectronicBillRepository electronicBillRepository;

    private final IGetPosResolutionUseCase getPosResolutionUseCase;

    @Override
    public void invoke(
        String token,
        TransactionRequestDTO transactionRequest
    ) {

        final boolean isEnabledElectronicBillStore =
            electronicBillStoreIds.stream()
                .anyMatch(id -> transactionRequest.getOrders().stream()
                    .anyMatch(order -> order.getStore().getId().equals(id))
            );

        if(isEnabledElectronicBillStore){
            Optional.ofNullable(transactionRequest.getCompany().getId())
                .filter(id -> id == CompanyEnum.ROBIN_FOOD_COLOMBIA.getId())
                .ifPresent((Long idCompany) -> {
                    getPosResolutionUseCase.invoke(token, transactionRequest);
                    electronicBillRepository.sendElectronicBill(token, transactionRequest);
                });
        }
    }
}
