package com.project.memor2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Memor2Application {

    public static void main(String[] args) {
        SpringApplication.run(Memor2Application.class, args);
    }

}