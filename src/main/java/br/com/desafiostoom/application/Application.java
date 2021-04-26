package br.com.desafiostoom.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("br.com.desafiostoom.application.data.entity")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
