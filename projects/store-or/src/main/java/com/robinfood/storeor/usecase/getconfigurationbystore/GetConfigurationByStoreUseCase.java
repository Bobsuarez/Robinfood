package com.robinfood.storeor.usecase.getconfigurationbystore;

import com.robinfood.storeor.dtos.CommandConfiguration.CommandConfigurationResponseDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.usecase.getbillingconfigurationbystore.IGetBillingConfigurationByStoreUseCase;
import com.robinfood.storeor.usecase.getcommandconfigurationbystoreid.IGetCommandConfigurationByStoreIdUseCase;
import com.robinfood.storeor.usecase.getconfigurationposbystore.IGetConfigurationPosByStoreUseCase;
import com.robinfood.storeor.usecase.getstorebyidusecase.IGetStoreByIdUseCase;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class GetConfigurationByStoreUseCase implements IGetConfigurationByStoreUseCase {

    private final IGetStoreByIdUseCase getStoreByIdUseCase;
    private final IGetBillingConfigurationByStoreUseCase getBillingConfigurationByStoreUseCase;
    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IGetCommandConfigurationByStoreIdUseCase getCommandConfigurationByStoreIdUseCase;
    private final IGetConfigurationPosByStoreUseCase getConfigurationPosByStoreUseCase;

    public GetConfigurationByStoreUseCase(
            IGetStoreByIdUseCase getStoreByIdUseCase,
            IGetBillingConfigurationByStoreUseCase getBillingConfigurationByStoreUseCase,
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IGetCommandConfigurationByStoreIdUseCase getCommandConfigurationByStoreIdUseCase,
            IGetConfigurationPosByStoreUseCase getConfigurationPosByStoreUseCase) {
        this.getStoreByIdUseCase = getStoreByIdUseCase;
        this.getBillingConfigurationByStoreUseCase = getBillingConfigurationByStoreUseCase;
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.getCommandConfigurationByStoreIdUseCase = getCommandConfigurationByStoreIdUseCase;
        this.getConfigurationPosByStoreUseCase = getConfigurationPosByStoreUseCase;
    }

    @Override
    public StoreResponseDTO invoke(Long storeId, Boolean includePos) {

        log.info("Execute GetConfigurationByStoreUseCase by store: {}", storeId);

        log.info("token is generated");

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        log.info("get store with id {}", storeId);

        StoreResponseDTO store = getStoreByIdUseCase.invoke(storeId, token.getAccessToken());

        log.info("store data response {}", store);

        if (includePos.equals(Boolean.TRUE)){
            log.info("get list pos by store with id {}", storeId);
            List<StorePosDTO> storePosDTOS = getConfigurationPosByStoreUseCase.invoke(storeId);
            store.setPos(storePosDTOS);
            log.info("pos by store data response {}", storePosDTOS);
        }

        log.info("get commandConfiguration with storeId {}", storeId);

        List<CommandConfigurationResponseDTO> commandConfiguration =getCommandConfigurationByStoreIdUseCase
                .invoke(storeId,token.getAccessToken());

        log.info("commandConfiguration data response {}", commandConfiguration);

        log.info("get billing configuration with store id {}", storeId);

        TreasureDepartmentsDTO treasureDepartmentsDTO = getBillingConfigurationByStoreUseCase.invoke(storeId,
                token.getAccessToken());

        log.info("billing configuration store data response {}", treasureDepartmentsDTO);

        store.setTreasuryDepartment(treasureDepartmentsDTO);
        store.setCommandConfiguration(commandConfiguration);

        return store;
    }

    @Override
    public Page<StoreResponseDTO> invoke(String name, Long companyCountryId, Integer page,
        Integer size, Sort sort) {

        log.info("Execute GetConfigurationByStoreUseCase by store list.");

        TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        return getStoreByIdUseCase.invoke(name, companyCountryId, page, size,
            sort, token.getAccessToken());
    }
}
