package com.carcompany.web_project;

import com.carcompany.web_project.seeds.SeedData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class WebProjectApplication {

    private final SeedData seedData;

    public static void main(String[] args) {
        SpringApplication.run(WebProjectApplication.class, args);
    }

    @EventListener
    public void seedData(ContextRefreshedEvent event) {
        seedData.seedSaloonData();
    }
}
