package ru.aniskov.petproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public TestDataObject testDataObject(){
      return new TestDataObject();
   }
}
