package ru.aniskov.petproject.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.configuration.BeanConfiguration;

@Component
public class ApiConsumer {

    @Scheduled(cron = "${api.consume.time}")
    public void run() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        CommandLineRunner runner = context.getBean(CommandLineRunner.class);
        runner.run();
    }

}
