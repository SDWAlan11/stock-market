package com.market.stock.bsn;

import com.market.stock.data.entity.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaTopicListener {

    @Resource
    private QuoteService quoteService;

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenTopic1(Quote quote) {
        System.out.println("Recieved Message of topic1 in  listener: " + quote);

        quoteService.addQuote(quote);
    }
}
