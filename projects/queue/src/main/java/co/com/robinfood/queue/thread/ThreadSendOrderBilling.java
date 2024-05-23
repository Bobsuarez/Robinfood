package co.com.robinfood.queue.thread;

import co.com.robinfood.queue.network.OrderBillingAPI;
import co.com.robinfood.queue.utils.ObjectMapperSingleton;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ThreadSendOrderBilling implements Runnable {

    private final List<JsonNode> transactionList;
    private final String token;
    private final OrderBillingAPI orderBillingAPI;

    public ThreadSendOrderBilling(List<JsonNode> transactionList, String token, OrderBillingAPI orderBillingAPI) {
        this.transactionList = transactionList;
        this.token = token;
        this.orderBillingAPI = orderBillingAPI;
    }

    @Override
    public void run() {
        transactionList.forEach((data) -> sendOrderBilling(data, token));
    }

    private void sendOrderBilling(JsonNode transactionDTO, String token) {

        try {
            var responseApi = orderBillingAPI.saveOrdersBilling(token, transactionDTO);
            log.info("Successfully sent the order with reply : {}", ObjectMapperSingleton.objectToJson(responseApi));
            Thread.sleep(20000); // 1 Seconds of waiting
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Error sending order billing : {}", ObjectMapperSingleton.objectToJson(transactionDTO));
        }
    }

}
