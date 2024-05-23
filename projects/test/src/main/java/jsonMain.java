import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.ObjectMapperSingletonUtil;
import org.example.dtos.ChangeStatusDTO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class jsonMain {

    private static final int THREAD_COUNT = 4;
    private static final int SubListSize = 10;

    public static final String TARGET_BACKSLASH = "\\";
    public static final String TARGET_DOUBLE_BACKSLASH = "\\\\";

    public static final int DEFAULT_BOOLEAN_FALSE_VALUE_AS_INT = 0;
    public static final int DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT = 1;

    private static final Pattern compilePattern = Pattern.compile("^(\"\\{)");

    public static void main(String[] args) throws JsonProcessingException {

//        String data = "[{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"},{\"orderId\":5486810,\"statusId\":2,\"notes\":\"Sin notas\",\"statusCode\":\"ORDER_IN_COURSE\",\"userId\":1,\"origin\":\"Local Server\"}]";
//
//        String data = "\"{\\\"channelId\\\":4,\\\"couponReference\\\":null,\\\"dateTime\\\":\\\"2023-10-27T17:37:43" +
//                ".198834364\\\",\\\"integrationId\\\":null,\\\"orderId\\\":5493925,\\\"orderUid\\\":null,\\\"orderUuid\\\":\\\"d334729f-4037-4487-a5a9-01c597e0aa6d\\\",\\\"statusCode\\\":\\\"ORDER_IN_PROGESS\\\",\\\"transactionId\\\":2365388,\\\"transactionUuid\\\":\\\"cea7c0d8-89c6-4743-b5ed-2c64b3f6914e\\\"}\"";

        String data = "{\"channelId\":4,\"couponReference\":null,\"dateTime\":\"2023-10-27T17:37:43.198834364\"," +
                "\"integrationId\":null,\"orderId\":5493925,\"orderUid\":null,\"orderUuid\":\"d334729f-4037-4487-a5a9-01c597e0aa6d\",\"statusCode\":\"ORDER_IN_PROGESS\",\"transactionId\":2365388,\"transactionUuid\":\"cea7c0d8-89c6-4743-b5ed-2c64b3f6914e\"}";


        final String event = execute(data);
        final ChangeStatusDTO changeStatusDTO = ObjectMapperSingletonUtil.jsonToClass(event, ChangeStatusDTO.class);
        System.out.println(ObjectMapperSingletonUtil.objectToJson(changeStatusDTO));
    }


    public static String execute(String messageActiveMQEvent) {

        messageActiveMQEvent = deleteBackslash(TARGET_BACKSLASH, messageActiveMQEvent);

        String isValidateJson = messageActiveMQEvent.substring(0, 2);
        if (isValidateJson.equals("\"{")) {
            messageActiveMQEvent = deleteFirstAndLastCharacter(messageActiveMQEvent);
        }
        return messageActiveMQEvent;
    }

    private static String deleteBackslash(String target, String message) {

        return message.replace(TARGET_BACKSLASH, "");
    }

    private static String deleteFirstAndLastCharacter(String message) {

        return message.substring(
                DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT,
                message.length() - DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT
        );
    }

    private static <T> List<List<T>> splitListData(List<T> tList) {

        return IntStream.range(0, (tList.size() + SubListSize - 1) / SubListSize)
                .mapToObj(i -> tList.subList(i * SubListSize, Math.min(SubListSize * (i + 1), tList.size())))
                .collect(Collectors.toList());

    }

    private static void executeThread(List<ChangeStatusDTO> orderDetailDTOS, String url) {

        List<List<ChangeStatusDTO>> groupByOrderDetail = splitListData(orderDetailDTOS);

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < groupByOrderDetail.size(); i++) {
            Runnable threadFirst = new ThreadTest(groupByOrderDetail.get(i), "Hilo " + (i + 1));
            executorService.execute(threadFirst);
            System.out.println(groupByOrderDetail.get(i).size());
        }

        groupByOrderDetail.forEach(data -> {
                }
        );

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Esperar a que todos los hilos finalicen
        }

        System.out.println("Todos los hilos han terminado.");
    }

    static class ThreadTest implements Runnable {
        private final List<ChangeStatusDTO> orderDetailDTOS;
        private String nameThread;

        public ThreadTest(List<ChangeStatusDTO> orderDetailDTOS, String s) {
            this.orderDetailDTOS = orderDetailDTOS;
            this.nameThread = s;
        }

        @Override
        public void run() {
            try {
                orderDetailDTOS.forEach(data -> System.out.println(nameThread + ": " + ObjectMapperSingletonUtil.objectToJson(data)));
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
