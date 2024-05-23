package com.robinfood.app.configs;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.DefaultTableNameResolver;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Configuration
@EnableDynamoDBRepositories(
        basePackages = "com.robinfood.repositories.dynamodb.transactioncreated",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class)
)
public class DynamoDBConfiguration {

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.sessiontoken:default}")
    private String amazonAWSSessionToken;

    @Value("${amazon.aws.region}")
    private String amazonAWSRegion;

    @Value("${amazon.aws.dynamo.temporaryTransactionTableName}")
    private String temporaryTransactionTableName;

    public DynamoDBConfiguration() {
        // this constructor is empty because it is a configuration class
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider amazonAWSCredentialsProvider) {

        return AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(amazonAWSRegion)
                .withCredentials(amazonAWSCredentialsProvider).build();
    }

    @Profile("local")
    @Bean
    public AWSCredentialsProvider amazonAWSCredentialsProviderLocal() {

        return new AWSStaticCredentialsProvider(
                new BasicSessionCredentials(amazonAWSAccessKey,amazonAWSSecretKey,amazonAWSSessionToken)
        );
    }

    @Profile("!local")
    @Bean
    public AWSCredentialsProvider amazonAWSCredentialsProvider() {

        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
    }

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig(TableNameOverride tableNameOverrider) {
        // Create empty DynamoDBMapperConfig builder
        DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        // Inject missing defaults from the deprecated method
        builder.withTypeConverterFactory(DynamoDBTypeConverterFactory.standard());
        builder.withTableNameResolver(DefaultTableNameResolver.INSTANCE);
        // Inject the table name overrider bean
        builder.withTableNameOverride(tableNameOverrider());
        return builder.build();
    }

    @Bean
    public TableNameOverride tableNameOverrider() {
        return TableNameOverride.withTableNameReplacement(temporaryTransactionTableName);
    }
}
