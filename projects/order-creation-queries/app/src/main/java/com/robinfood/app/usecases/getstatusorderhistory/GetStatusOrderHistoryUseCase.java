package com.robinfood.app.usecases.getstatusorderhistory;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import com.robinfood.core.dtos.user.UserDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.statusorderhistory.StatusOrderHistoryMappers;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.statusorderhistory.IStatusOrderHistoryRepository;
import com.robinfood.repository.user.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetStatusOrderHistoryUseCase implements IGetStatusOrderHistoryUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IStatusOrderHistoryRepository statusOrderHistoryRepository;

    private final IUserRepository userRepository;

    public GetStatusOrderHistoryUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IStatusOrderHistoryRepository statusOrderHistoryRepository,
            IUserRepository userRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.statusOrderHistoryRepository = statusOrderHistoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<StatusOrderHistoryDTO> invoke(String uuid) {

        final TokenModel token = getTokenBusinessCapabilityUseCase.invoke();

        Result<List<StatusOrderHistoryDTO>> statusHistoryResult = statusOrderHistoryRepository.getStatusOrderHistory(
                token.getAccessToken(), uuid
        );
        findError(statusHistoryResult);

        final List<StatusOrderHistoryDTO> statusOrderHistoryDTOS =
                ((Result.Success<List<StatusOrderHistoryDTO>>) statusHistoryResult).getData();

        final List<Long> userIds = statusOrderHistoryDTOS.stream()
                .map(StatusOrderHistoryDTO::getUserId)
                .collect(Collectors.toList());

        final Result<List<UserDTO>> usersResult = userRepository.getUsersByIds(token.getAccessToken(), userIds);
        findError(usersResult);

        List<UserDTO> userDTOList = ((Result.Success<List<UserDTO>>) usersResult).getData();

        return statusOrderHistoryDTOS.stream()
                .map((StatusOrderHistoryDTO status) -> StatusOrderHistoryMappers.buildUserData(status, userDTOList))
                .collect(Collectors.toList());
    }

    private <T> void findError(T objectEntity) {

        if (objectEntity instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) objectEntity).getHttpStatus(),
                    ((Result.Error) objectEntity).getException().getMessage()
            );
        }
    }
}
