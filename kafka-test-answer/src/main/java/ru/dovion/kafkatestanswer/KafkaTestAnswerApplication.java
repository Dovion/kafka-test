package ru.dovion.kafkatestanswer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.*;
@SpringBootApplication
@Slf4j
@EnableKafka
public class KafkaTestAnswerApplication {


    public static void main(String[] args) {
        SpringApplication.run(KafkaTestAnswerApplication.class, args);
//        AnswerService answerService = new AnswerService();
//        while (true){
//            answerService.answer();
//        }
    }
}
