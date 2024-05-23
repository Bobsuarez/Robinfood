package com.robinfood.changestatusbc.usecases.getallstate;

import com.robinfood.changestatusbc.entities.StatusEntity;
import com.robinfood.changestatusbc.repositories.status.IStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetAllStateUseCase implements IGetAllStateUseCase{

    private final IStatusRepository statusRepository;

    public GetAllStateUseCase(IStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<StatusEntity> invoke() {

        log.info("Getting all states");
        return  (List<StatusEntity>) statusRepository.findAll();
    }
}
