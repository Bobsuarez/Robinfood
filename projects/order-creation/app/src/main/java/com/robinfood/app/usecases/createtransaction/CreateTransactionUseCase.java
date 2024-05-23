package com.robinfood.app.usecases.createtransaction;

import com.robinfood.app.usecases.getbrandsbycountryid.IGetBrandsByCountryIdUseCase;
import com.robinfood.app.usecases.setordersmultibrand.ISetOrdersMultiBrandUseCase;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.ConfigTransactionResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.enums.logs.OrderLogEnum;
import com.robinfood.core.models.domain.menu.Brand;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.repository.transaction.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;
import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
@Slf4j
public class CreateTransactionUseCase implements ICreateTransactionUseCase {

    private final ITransactionRepository transactionRepository;
    private final IGetBrandsByCountryIdUseCase getBrandsByCountryIdUseCase;
    private final ISetOrdersMultiBrandUseCase setOrdersMultiBrandUseCase;

    public CreateTransactionUseCase(
            ITransactionRepository transactionRepository,
            IGetBrandsByCountryIdUseCase getBrandsByCountryIdUseCase,
            ISetOrdersMultiBrandUseCase setOrdersMultiBrandUseCase
    ) {
        this.transactionRepository = transactionRepository;
        this.getBrandsByCountryIdUseCase = getBrandsByCountryIdUseCase;
        this.setOrdersMultiBrandUseCase = setOrdersMultiBrandUseCase;
    }

    private void updateOrders(TransactionRequestDTO transactionRequestDTO, List<OrderResponseDTO> orderResponseDTOS) {

        IntStream.range(GlobalConstants.DEFAULT_INTEGER_VALUE, orderResponseDTOS.size()).forEach((int index) -> {

                    final OrderResponseDTO currentOrderCreated = orderResponseDTOS.get(index);
                    final OrderDTO currentInputOrder = transactionRequestDTO.getOrders().get(index);
                    final Long currentOrderId = currentOrderCreated.getId();

                    setOrdersFields(currentInputOrder, currentOrderCreated, currentOrderId);
                }
        );
    }

    private void setOrdersFields(
            OrderDTO orderDTO,
            OrderResponseDTO orderResponseDTO,
            Long orderId
    ) {
        orderDTO.setId(orderId);
        orderDTO.setUid(orderResponseDTO.getUid());
    }

    private void getTransactionCreationResponseDTO(
            TransactionRequestDTO transactionRequestDTO,
            TransactionCreationResponseDTO transactionCreationResponseDTO
    ) {

        final List<OrderResponseDTO> orderResponseDTOS = transactionCreationResponseDTO
                .getTransaction().getOrders();

        transactionRequestDTO.setId(transactionCreationResponseDTO.getTransaction().getId());
        transactionRequestDTO.setOrderCreatedAt(transactionCreationResponseDTO.getTransaction().getCreatedAt());
        transactionRequestDTO.getOrders().get(0).setInvoiceNumber(
                transactionCreationResponseDTO.getTransaction().getOrders().get(0).getOrderInvoiceNumber()
        );

        if (orderResponseDTOS.size() == transactionRequestDTO.getOrders().size()) {
            updateOrders(transactionRequestDTO, orderResponseDTOS);
        }
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequestDTO
    ) {
        List<Brand> brands = getBrandsByCountryIdUseCase.invoke(
                token,
                transactionRequestDTO.getCompany().getId()
        );

        setOrdersMultiBrandUseCase.invoke(transactionRequestDTO.getOrders());

        showMessageTransactionAccepted(transactionRequestDTO);

        CompletableFuture<TransactionCreationResponseDTO> transactionCreationFuture =
                transactionRepository.createTransaction(
                        token,
                        brands,
                        transactionRequestDTO
                );

        return allOf(transactionCreationFuture).thenApply((Void unused) ->
            createdTransaction(transactionCreationFuture,transactionRequestDTO)
        ).join();
    }

    private CompletableFuture<TransactionCreationResult> createdTransaction(
            CompletableFuture<TransactionCreationResponseDTO> transactionCreationFuture,
            TransactionRequestDTO transactionRequestDTO
    ) {

        final TransactionCreationResponseDTO transactionCreationResponseDTO = transactionCreationFuture.join();

        log.info("Transaction created: {}", objectToJson(transactionCreationResponseDTO));

        SaveDataInMemoryUtil.setData(
                transactionCreationResponseDTO.getTransaction().getUuid(),
                transactionCreationResponseDTO
        );

        getTransactionCreationResponseDTO(transactionRequestDTO, transactionCreationResponseDTO);

        transactionRepository.setTransactionResponseDTO(new ConfigTransactionResponseDTO(
                transactionCreationResponseDTO));

        return completedFuture(new TransactionCreationResult.TransactionCreated(
                transactionCreationResponseDTO));
    }

    private void showMessageTransactionAccepted(TransactionRequestDTO transactionRequest) {
        if (Objects.isNull(transactionRequest.getId())) {
            log.info(
                    OrderLogEnum.ORDER_TRANSACTION_ACCEPTED.getMessage() + " {}",
                    objectToJson(transactionRequest)
            );
        }
    }
}
