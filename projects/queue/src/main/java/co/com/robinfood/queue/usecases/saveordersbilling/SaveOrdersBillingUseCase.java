package co.com.robinfood.queue.usecases.saveordersbilling;

import co.com.robinfood.queue.network.OrderBillingAPI;
import co.com.robinfood.queue.persistencia.entity.token.TokenModelEntity;
import co.com.robinfood.queue.thread.ThreadSendOrderBilling;
import co.com.robinfood.queue.usecases.token.TokenToBusinessCapabilityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static co.com.robinfood.queue.view.util.DataUtil.splitListData;


@Slf4j
@Service
public class SaveOrdersBillingUseCase implements ISaveOrdersBillingUseCase {

    private static final int THREAD_COUNT = 4;

    private final TokenToBusinessCapabilityRepository tokenToBusinessCapabilityRepository;

    private final OrderBillingAPI orderBillingAPI;

    @Autowired
    public SaveOrdersBillingUseCase(
            TokenToBusinessCapabilityRepository tokenToBusinessCapabilityRepository, OrderBillingAPI orderBillingAPI
    ) {
        this.tokenToBusinessCapabilityRepository = tokenToBusinessCapabilityRepository;
        this.orderBillingAPI = orderBillingAPI;
    }

    @Override
    public void invoke(List<JsonNode> requestPayloadList) {

        TokenModelEntity tokenResponseEntity = tokenToBusinessCapabilityRepository.get();
        executeThread(requestPayloadList, tokenResponseEntity.getAccessToken());
    }

    private void executeThread(List<JsonNode> requestPayloadList, String token) {

        List<List<JsonNode>> groupByOrderDetail = splitListData(requestPayloadList);

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        groupByOrderDetail.forEach(
                data -> {
                    Runnable threadFirst = new ThreadSendOrderBilling(data, token, orderBillingAPI);
                    executorService.execute(threadFirst);

                });

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Esperar a que todos los hilos finalicen
        }
        log.info("Todos los hilos han terminado.");
    }

}
