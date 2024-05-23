package co.com.robinfood.queue.repository;

import co.com.robinfood.queue.persistencia.dto.OrderBillingJsonResponseDTO;

import java.util.List;

public interface IOrderElectronicRepository {

    List<OrderBillingJsonResponseDTO> findAllOrdersByStatusCodeAndDateInitialAndDateFinal(String dateToString);
}
