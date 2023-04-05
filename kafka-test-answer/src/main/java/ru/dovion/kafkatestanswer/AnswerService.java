package ru.dovion.kafkatestanswer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class AnswerService {

    Properties props;
    KafkaConsumer<String, String> consumer;

    Properties kafkaProps = new Properties();

    KafkaProducer<String ,String> producer;

    AnswerService() {
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singletonList("ask"));
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(kafkaProps);
    }

    public void answer(String s ) {
        s = s.toUpperCase();
        ask(s);
    }

    public void ask(String ask){
        ProducerRecord<String, String> record =
                new ProducerRecord<>("answer", "task",ask);
        producer.send(record, new ProducerCallback());
        log.info("Message sent");

    }

}
