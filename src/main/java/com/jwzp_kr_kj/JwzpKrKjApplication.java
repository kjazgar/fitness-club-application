package com.jwzp_kr_kj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JwzpKrKjApplication {

    private static final Logger LOGGER = LogManager.getLogger(JwzpKrKjApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JwzpKrKjApplication.class, args);
        LOGGER.info(Logs.logStart());
    }

}
