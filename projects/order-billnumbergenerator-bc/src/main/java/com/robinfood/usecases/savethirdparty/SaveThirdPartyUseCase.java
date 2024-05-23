package com.robinfood.usecases.savethirdparty;

import com.robinfood.dtos.sendordertosimba.request.ThirdPartyDTO;
import com.robinfood.entities.OrderThirdPartiesEntity;
import com.robinfood.mappers.ThirdPartyRequestDTOToEntityMapper;
import com.robinfood.repository.thirdparty.IOrderThirdPartiesRepository;
import com.robinfood.repository.thirdparty.OrderThirdPartiesRepository;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import lombok.AllArgsConstructor;

import static com.robinfood.enums.AppLogsTraceEnum.INIT_THIRD_PARTY;
import static com.robinfood.enums.AppLogsTraceEnum.SAVED_THIRD_PARTY;

@AllArgsConstructor
public class SaveThirdPartyUseCase implements ISaveThirdPartyUseCase {

    private final IOrderThirdPartiesRepository orderThirdPartiesRepository;

    public SaveThirdPartyUseCase() {
        this.orderThirdPartiesRepository = OrderThirdPartiesRepository.getInstance();
    }

    @Override
    public void invoke(Long orderId, ThirdPartyDTO thirdPartyDTO) {

        LogsUtil.info(
                INIT_THIRD_PARTY.getMessageWithCode(),
                orderId,
                ObjectMapperSingleton.objectToJson(thirdPartyDTO)
        );

        OrderThirdPartiesEntity thirdPartiesEntity = ThirdPartyRequestDTOToEntityMapper
                .buildToThirdPartyEntity(orderId, thirdPartyDTO);

        orderThirdPartiesRepository.save(thirdPartiesEntity);

        LogsUtil.info(
                SAVED_THIRD_PARTY.getMessageWithCode()
        );
    }
}
