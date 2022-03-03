package com.jwzp_kr_kj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JwzpKrKjApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwzpKrKjApplication.class, args);
    }

}
