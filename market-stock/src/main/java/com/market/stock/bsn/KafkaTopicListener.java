package com.market.stock.bsn;

import com.market.stock.data.entity.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaTopicListener {

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenTopic1(Quote message) {
        System.out.println("Recieved Message of topic1 in  listener: " + message);
    }
}
