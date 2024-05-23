package co.com.robinfood.queue.bussiness;

import co.com.robinfood.queue.persistencia.dto.DataRequestQueue;
import co.com.robinfood.queue.persistencia.dto.orderdetail.OrderDetailDTO;
import co.com.robinfood.queue.thread.ThreadSendSQS;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static co.com.robinfood.queue.view.util.DataUtil.splitListData;

@Slf4j
@AllArgsConstructor
@Component
public class SendSQS {

    private static final int THREAD_COUNT = 4;

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    public void sendMessage(DataRequestQueue dataRequestQueue) {

        messagingTemplate = queueMessagingTemplate(dataRequestQueue);

        executeThread(dataRequestQueue.getInformationMessageSQS(), dataRequestQueue.getBrokerURL());
    }

    private void executeThread(List<OrderDetailDTO> orderDetailDTOS, String url) {

        List<List<OrderDetailDTO>> groupByOrderDetail = splitListData(orderDetailDTOS);

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        groupByOrderDetail.forEach(data -> {
                    Runnable threadFirst = new ThreadSendSQS(messagingTemplate, data, url);
                    executorService.execute(threadFirst);
                }
        );

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Esperar a que todos los hilos finalicen
        }

        log.info("Todos los hilos han terminado.");
    }

    private AmazonSQSAsync amazonSQSAsyncClientBuilder(DataRequestQueue dataRequestQueue) {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion("us-east-2")
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(dataRequestQueue.getUserQueue(), dataRequestQueue.getPassQueue())))
                .build();
    }

    public QueueMessagingTemplate queueMessagingTemplate(DataRequestQueue dataRequestQueue) {
        return new QueueMessagingTemplate(amazonSQSAsyncClientBuilder(dataRequestQueue));
    }
}
