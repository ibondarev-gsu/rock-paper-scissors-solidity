package com.peartech;

import com.peartech.service.GameV2Service;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameV2Application {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(GameV2Application.class, args);
    }

}
