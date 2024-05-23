package com.robinfood.usecases.saveelectronicbillings;

import com.robinfood.entities.OrderElectronicBillingsEntity;
import com.robinfood.repository.electronicbillings.ElectronicBillingsRepository;
import com.robinfood.repository.electronicbillings.IElectronicBillingsRepository;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import lombok.AllArgsConstructor;

import static com.robinfood.enums.AppLogsTraceEnum.INIT_ELECTRONIC_BILLING;
import static com.robinfood.enums.AppLogsTraceEnum.SAVED_ELECTRONIC_BILLING;

@AllArgsConstructor
public class SaveElectronicBillingsUseCase implements ISaveElectronicBillingsUseCase {

    private final IElectronicBillingsRepository electronicBillingsRepository;

    public SaveElectronicBillingsUseCase() {
        this.electronicBillingsRepository = new ElectronicBillingsRepository();
    }

    @Override
    public void invoke(OrderElectronicBillingsEntity orderElectronicBillingsEntity) {

        LogsUtil.info(
                INIT_ELECTRONIC_BILLING.getMessageWithCode(),
                orderElectronicBillingsEntity.getOrder_id(),
                ObjectMapperSingleton.objectToJson(orderElectronicBillingsEntity)
        );

        electronicBillingsRepository.save(orderElectronicBillingsEntity);

        LogsUtil.info(
                SAVED_ELECTRONIC_BILLING.getMessageWithCode()
        );
    }
}
