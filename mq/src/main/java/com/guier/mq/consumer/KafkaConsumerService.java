package com.guier.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerService {
    @KafkaListener(groupId = "group02", topics = "topic02")
    public void onMsg(ConsumerRecord<String, Object> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        log.info("消费端接收消息：{}", record.value());
        acknowledgment.acknowledge();
    }
}
