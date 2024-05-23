package com.robinfood.publisher;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.enums.ErrorLogsEnum;
import com.robinfood.exceptions.DataNotFoundException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingletonUtil;

import java.util.HashMap;
import java.util.Map;

import static com.robinfood.constants.APIConstants.AWS_ACCESS_KEY_DIDI;
import static com.robinfood.constants.APIConstants.AWS_REGION_DIDI;
import static com.robinfood.constants.APIConstants.AWS_SECRET_KEY_DIDI;
import static com.robinfood.constants.APIConstants.AWS_SQS_POINT_URL;
import static com.robinfood.constants.GeneralConstants.MESSAGE_COUNTRY_OUT;
import static com.robinfood.constants.GeneralConstants.MESSAGE_FROM_OUT;
import static com.robinfood.constants.GeneralConstants.NAME_APPLICATION;
import static com.robinfood.enums.AppLogsTraceEnum.SEND_MESSAGE_AMQ;

public class SendMessageSQS {

    public void invoke(OrderToCreateDTO transactionDTO, String messageFrom, String messageCountry) {

        final String messageBody = ObjectMapperSingletonUtil.objectToJson(transactionDTO);

        try {
            AmazonSQS amazonSQS = amazonSQSAsyncClientBuilder();

            SendMessageRequest sendMessageRequest = new SendMessageRequest(AWS_SQS_POINT_URL, messageBody);

            Map<String, MessageAttributeValue> attributeValue = new HashMap<>();

            attributeValue.put(MESSAGE_FROM_OUT, new MessageAttributeValue()
                    .withDataType("String")
                    .withStringValue(messageFrom));

            attributeValue.put(MESSAGE_COUNTRY_OUT, new MessageAttributeValue()
                    .withDataType("String")
                    .withStringValue(messageCountry));

            sendMessageRequest.setMessageAttributes(attributeValue);

            sendMessageRequest.setMessageGroupId(NAME_APPLICATION);

            final SendMessageResult sendMessageResponse = amazonSQS.sendMessage(sendMessageRequest);

            LogsUtil.info(SEND_MESSAGE_AMQ.getMessage(), sendMessageResponse.getMessageId());

        } catch (Exception e) {
            LogsUtil.error(ErrorLogsEnum.ERROR_SENT_SQS.getMessageWithCode(), e);
            throw new DataNotFoundException(ResponseMapper.buildWithError(
                    HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                    e.getMessage(),
                    Boolean.TRUE
            ), e.getMessage());
        }

    }

    private AmazonSQS amazonSQSAsyncClientBuilder() {
        return AmazonSQSClientBuilder
                .standard()
                .withRegion(AWS_REGION_DIDI)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(AWS_ACCESS_KEY_DIDI, AWS_SECRET_KEY_DIDI)))
                .build();
    }

}
