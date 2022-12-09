package com.example.demo;

import com.amazonaws.services.dynamodbv2.local.server.LocalDynamoDBRequestHandler;
import com.amazonaws.services.dynamodbv2.local.server.LocalDynamoDBServerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbLocalConfig {

    @Bean
    LocalDynamoDBServerHandler localDynamoDBServerHandler() {
        var handler = new LocalDynamoDBRequestHandler(0, true, null, false, false);
        return new LocalDynamoDBServerHandler(handler, null);
    }

}
