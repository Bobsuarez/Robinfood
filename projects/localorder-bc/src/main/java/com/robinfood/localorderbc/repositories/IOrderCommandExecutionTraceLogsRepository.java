package com.robinfood.localorderbc.repositories;

import com.robinfood.localorderbc.entities.OrderCommandExecutionTraceLogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderCommandExecutionTraceLogsRepository
        extends JpaRepository<OrderCommandExecutionTraceLogsEntity, Long> {

}
