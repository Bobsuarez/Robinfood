package com.robinfood.storeor.usecase.getposconfiguration;

import com.robinfood.storeor.dtos.UserStoreConfigurationsResponseDTO;
import com.robinfood.storeor.dtos.response.PosResponseDTO;
import com.robinfood.storeor.dtos.user.PosConfigurationResponseDTO;
import com.robinfood.storeor.dtos.user.UserPosResponseDTO;
import com.robinfood.storeor.dtos.user.UserStoreResponseDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.enums.DeliveryEnum;
import com.robinfood.storeor.usecase.getconfigurationbypos.IGetConfigurationByPosUseCase;
import com.robinfood.storeor.usecase.getstorebyidusecase.IGetStoreByIdUseCase;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetPosConfigurationByUserIdUseCase implements IGetPosConfigurationByUserIdUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IGetStoreByIdUseCase getStoreByIdUseCase;
    private final IGetConfigurationByPosUseCase getConfigurationByPosUseCase;

    public static final int FLOW_ID = 5;

    public GetPosConfigurationByUserIdUseCase(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
                                              IGetStoreByIdUseCase getStoreByIdUseCase,
                                              IGetConfigurationByPosUseCase getConfigurationByPosUseCase) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.getStoreByIdUseCase = getStoreByIdUseCase;
        this.getConfigurationByPosUseCase = getConfigurationByPosUseCase;
    }

    @Override
    public PosConfigurationResponseDTO invoke(Long userId) {

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        UserStoreConfigurationsResponseDTO userStore = getStoreByIdUseCase.findStoreByUserId(
                userId,
                token.getAccessToken()
        );
        log.info("Response userStore " + userStore);

        PosResponseDTO posResponseDTO = this.getConfigurationByPosUseCase.invoke(userStore.getStore().getId(), userId);

        DeliveryEnum status = DeliveryEnum.valueOfIdPos(posResponseDTO.getPosTypes().getId());

        log.info("Response posResponseDTO " + posResponseDTO);

        final UserPosResponseDTO userPosResponseDTO = UserPosResponseDTO.builder()
                .id(posResponseDTO.getId())
                .flowId(FLOW_ID)
                .name(posResponseDTO.getName())
                .currency(userStore.getStore().getCurrencyType())
                .isDelivery(status.getDelivery())
                .isMultiBrand(true)
                .countryId(userStore.getStore().getCompany().getCountry().getId()).build();

        final UserStoreResponseDTO userStoreConfigurationsResponseDTO = UserStoreResponseDTO.builder()
                .address(userStore.getStore().getAddress())
                .city(userStore.getStore().getCity().getName())
                .country(userStore.getStore().getCompany().getCountry().getName())
                .id(userStore.getStore().getId())
                .internalName(userStore.getStore().getInternalName())
                .name(userStore.getStore().getName())
                .timeZone(userStore.getStore().getCity().getTimezone())
                .uuid(userStore.getStore().getUuid()).build();

        return PosConfigurationResponseDTO.builder()
                .pos(userPosResponseDTO)
                .store(userStoreConfigurationsResponseDTO).build();
    }
}
