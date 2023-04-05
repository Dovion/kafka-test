package ru.dovion.kafkatestasker;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.Properties;
import java.util.Scanner;

@Slf4j
@SpringBootApplication
@EnableKafka
public class KafkaTestAskerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestAskerApplication.class, args);
    }


}

