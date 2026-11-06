package com.ecohub.ecohubbac;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcohubbacApplication {

    public static void main(String[] args) {
        // .env faylını oxuyub sistem xüsusiyyətlərinə yükləyirik
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(EcohubbacApplication.class, args);
    }
}
