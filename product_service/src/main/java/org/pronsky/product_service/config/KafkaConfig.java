package org.pronsky.product_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic productCreatedTopic() {
        return TopicBuilder.name("product-created-events-topic")
                .partitions(1)
                .build();
    }
}
