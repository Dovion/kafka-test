package ru.dovion.kafkatestasker;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
public class AskService {
    Properties props;
    KafkaConsumer<String, String> consumer;

    Properties kafkaProps;

    KafkaProducer<String, String> producer;

    AskService() {
        kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(kafkaProps);
        props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-consumer-group-answer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Collections.singletonList("answer"));
    }

    public String ask(String ask) throws InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>("ask", "task", ask);
        producer.send(record, new ProducerCallback());
        log.info("Message sent");
//        var result = "";
//        while (true){
//            result = answer();
//            if (!result.isBlank()){
//                break;
//            }
//        }
        return answer();
    }

    public String answer() {
        ConsumerRecords<String, String> records = consumer.poll(100);///////////////////
//        consumer.seekToBeginning(List.of(new TopicPartition("answer",0)));

        if (records.count() == 0) {
            consumer.seekToEnd(List.of(new TopicPartition("answer", 0)));
            records = consumer.poll(100);
        }
        for (ConsumerRecord<String, String> record : records) {
            log.info("Key: " + record.key() + ", Value:" + record.value());
            log.info("Partition:" + record.partition() + ",Offset:" + record.offset());
            return record.value();
        }
        log.info("returns blank");
        return "";
    }


}
