package ru.aniskov.petproject.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;
import ru.aniskov.petproject.pojo.model.Quote;

public class QuoteCommandLineRunner implements CommandLineRunner {

    private RestTemplate restTemplate;
    private String url;

    private Logger _log = LoggerFactory.getLogger(QuoteCommandLineRunner.class.getName());

    public QuoteCommandLineRunner(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public void run(String... args) throws Exception {
        Quote quote = restTemplate.getForObject(
                this.url, Quote.class);
        _log.info(quote.toString());
    }
}
