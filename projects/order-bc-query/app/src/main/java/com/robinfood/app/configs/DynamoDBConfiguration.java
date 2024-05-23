package com.robinfood.app.configs;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {

    @Value("${amazon.aws.region}")
    private String amazonAWSRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider amazonAWSCredentialsProvider) {

        return AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(amazonAWSRegion)
                .withCredentials(amazonAWSCredentialsProvider).build();
    }
}
