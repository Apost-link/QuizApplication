package ru.aniskov.petproject.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import ru.aniskov.petproject.controller.ApiConsumer;
import ru.aniskov.petproject.scheduling.QuoteCommandLineRunner;

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class BeanConfiguration {

    @Autowired
    private Environment env;

    private Logger _log = LoggerFactory.getLogger(BeanConfiguration.class);

    @Bean
    public CommandLineRunner quoteCommandLineRunner(RestTemplate restTemplate) {
        return new QuoteCommandLineRunner(restTemplate, env.getProperty("api.consume.url"));
    }

    @Bean
    public ApiConsumer apiConsumer(CommandLineRunner commandLineRunner){
        return new ApiConsumer(commandLineRunner);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
