package ru.dovion.kafkatestanswer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/answ")
public class AnswerController {


    AnswerService answerService = new AnswerService();

    @KafkaListener(topics = "ask", groupId = "test-consumer-group")
    public void aVoid(@Payload String  result){
        answerService.answer(result);
    }
}
