package com.jwzp_kr_kj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.jwzp_kr_kj"})
@EntityScan(basePackages = "com.jwzp_kr_kj.core")
@EnableJpaRepositories(basePackages = "com.jwzp_kr_kj.repos")
public class JwzpKrKjApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwzpKrKjApplication.class, args);
    }

}
