package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.PaymentMethodChannelStore;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodChannelStoresRepository
    extends JpaRepository<PaymentMethodChannelStore, Long> {
    List<PaymentMethodChannelStore> findByStoreIdAndChannelIdAndOriginIdAndStatusAndDeletedAt(
        Long storeId,
        Long channelId,
        Long originId,
        Boolean status,
        LocalDateTime deletedAt
    );
}
