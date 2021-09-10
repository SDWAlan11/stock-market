package com.market.stock.bsn;

import com.market.stock.data.entity.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${kafka.topic.name}")
    private String topic;

    public void sendMessage(Object message) {

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(this.topic,"hey", message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("Message {} has been sent ", result );
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Something went wrong with the message {} ", message);
            }
        });
    }
}