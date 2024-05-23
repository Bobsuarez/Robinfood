package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.PaymentMethodStoreFlowChannel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * Campaign para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernan Ramirez
 */
@Repository
public interface PaymentMethodStoreFlowChannelsRepository extends JpaRepository<PaymentMethodStoreFlowChannel, Long> {

    List<PaymentMethodStoreFlowChannel> findByStoreIdAndChannelIdAndFlowIdAndStatusOrderByPositionAsc(
        Long storeId,
        Long channelId,
        Long flowId,
        boolean status
    );
}
