package ru.aniskov.petproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.configuration.BeanConfiguration;

@AllArgsConstructor
public class ApiConsumer {

    @Lazy
    private CommandLineRunner commandLineRunner;

    @Scheduled(cron = "${api.consume.time}")
    public void run() throws Exception {
        commandLineRunner.run();
    }

}
