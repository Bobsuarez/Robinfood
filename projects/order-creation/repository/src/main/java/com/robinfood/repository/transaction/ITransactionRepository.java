package com.robinfood.repository.transaction;

import com.robinfood.core.dtos.ConfigTransactionResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.models.domain.menu.Brand;
import com.robinfood.core.models.domain.pickuptime.PickupTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository that retrieves or sends all transaction data to the different sources of truth
 */
public interface ITransactionRepository {

    /**
     * Creates a transaction
     *
     * @param token              the authorization token
     * @param brands             brands from menu
     * @param transactionRequest the transaction with orders to create
     * @return the transaction creation result
     */

    CompletableFuture<TransactionCreationResponseDTO> createTransaction(
        String token,
        List<Brand> brands,
        TransactionRequestDTO transactionRequest
    );

    /**
     * Return an DTO of config transaction response dto
     *
     * @return the list of necessary steps to create an order
     */
    ConfigTransactionResponseDTO getTransactionResponseDTO();

    /**
     * Get the config transaction response dto
     *
     * @param configTransactionResponseDTO the config transaction response in DTO
     */
    void setTransactionResponseDTO(ConfigTransactionResponseDTO configTransactionResponseDTO);

    /**
     * Method that saves the pickup-time obtained by the orders
     *
     * @param token
     * @param pickupTime domain model
     * @return pickup-time saved
     */
    List<Long> savePickupTime(
        String token,
        PickupTime pickupTime,
        Long transactionId);

}
