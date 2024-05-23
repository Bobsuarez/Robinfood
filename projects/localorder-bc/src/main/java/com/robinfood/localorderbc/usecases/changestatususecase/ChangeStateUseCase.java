package com.robinfood.localorderbc.usecases.changestatususecase;

import com.robinfood.localorderbc.dtos.request.ChangeStateDTO;
import com.robinfood.localorderbc.entities.changestate.ChangeStateEntity;
import com.robinfood.localorderbc.entities.token.TokenModel;
import com.robinfood.localorderbc.mappers.IChangeStateMapper;
import com.robinfood.localorderbc.repositories.changestate.IChangeStateRepository;
import com.robinfood.localorderbc.usecases.gettokenuser.IGetOrchestratorTokenUserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChangeStateUseCase implements IChangeStateUseCase {

    private final IGetOrchestratorTokenUserUseCase getOrchestratorTokenUserUseCase;
    private final IChangeStateRepository changeStateRepository;
    private final IChangeStateMapper changeStateMapper;

    public ChangeStateUseCase(IGetOrchestratorTokenUserUseCase getOrchestratorTokenUserUseCase,
                              IChangeStateRepository changeStateRepository,
                              IChangeStateMapper changeStateMapper) {
        this.getOrchestratorTokenUserUseCase = getOrchestratorTokenUserUseCase;
        this.changeStateRepository = changeStateRepository;
        this.changeStateMapper = changeStateMapper;
    }

    @Override
    public ChangeStateDTO invoke(ChangeStateDTO changeStateDTO) {

        log.info("The status of the order will be changed with ChangeStateUseCase Dto {}", changeStateDTO);

        TokenModel tokenUser = getOrchestratorTokenUserUseCase.invoke();

        ChangeStateEntity changeStateEntity = changeStateRepository
                .responseChangeState(tokenUser.getAccessToken(), changeStateDTO);

        return changeStateMapper.changeStateDEntityChangeStateExecutionDTO(changeStateEntity);

    }
}
