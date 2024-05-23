package com.robinfood.localorderbc.repositories;

import com.robinfood.localorderbc.entities.OrderCommandExecutionEntity;
import com.robinfood.localorderbc.entities.OrderCommandExecutionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderCommandExecutionRepository
        extends JpaRepository<OrderCommandExecutionEntity, OrderCommandExecutionId> {

    @Query("select o.reprocessAttempt from OrderCommandExecutionEntity o " +
            "where o.orderId = :orderId and o.commandId = :commandId")
    Integer findByOrderIdAndCommandId(@Param("orderId") Long orderId, @Param("commandId") Long commandId);

    List<OrderCommandExecutionEntity> findByReprocess(Boolean reprocess);

}
