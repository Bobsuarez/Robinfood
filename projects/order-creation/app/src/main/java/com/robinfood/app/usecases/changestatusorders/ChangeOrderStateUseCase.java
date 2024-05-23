package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.UserDTO;
import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.dtos.staterespondto.StateChangeRespondDTO;
import com.robinfood.core.entities.changestateorderequestentities.ChangeStateOrderRespondEntity;
import com.robinfood.core.models.domain.Token;
import com.robinfood.repository.changestateorders.IChangeStateOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChangeOrderStateUseCase implements IChangeOrderStateUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    private final IChangeStateOrdersRepository changeStateOrdersRepository;

    public ChangeOrderStateUseCase(IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
                                   IChangeStateOrdersRepository changeStateOrdersRepository) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.changeStateOrdersRepository = changeStateOrdersRepository;
    }

    @Override
    public StateChangeRespondDTO invoke(StateChangeRequestDTO stateChangeRequestDTO) {

        Token token = getTokenBusinessCapabilityUseCase.invoke();

        changeByTheTokenUser(stateChangeRequestDTO);

        ChangeStateOrderRespondEntity changeStateOrderRespondEntity = changeStateOrdersRepository
                .invoke(stateChangeRequestDTO, token.getAccessToken()).join();

        final ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(changeStateOrderRespondEntity, StateChangeRespondDTO.class);
    }

    private void changeByTheTokenUser(StateChangeRequestDTO stateChangeRequestDTO) {
        Authentication authenticationContext = SecurityContextHolder.getContext().getAuthentication();
        final UserDTO user = (UserDTO) authenticationContext.getPrincipal();
        stateChangeRequestDTO.setUserId(Long.parseLong(user.getUsername()));
    }

}
