package com.robinfood.orderorlocalserver.configs.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSqs
public class SqsConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    public SqsConfig() {
        // this constructor is empty because it is a configuration class
    }

    private AmazonSQSAsync amazonSQSAsyncClientBuilder() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
                        )
                )
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
        return new QueueMessagingTemplate(amazonSQSAsyncClientBuilder());
    }
}
