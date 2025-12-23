package ru.vsu.bw.ledenev.labspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.vsu.bw.ledenev.labspring")
public class LabSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabSpringApplication.class, args);
    }

}
