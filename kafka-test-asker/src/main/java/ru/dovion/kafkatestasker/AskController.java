package ru.dovion.kafkatestasker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/task")
public class AskController {

    AskService askService;

    @Autowired
    AskController() {
        askService = new AskService();
    }

    @PostMapping
    public String doTask(@RequestParam String value) throws InterruptedException {
        return askService.ask(value);
    }
}
