package com.hydrogenhr;

import org.springframework.boot.SpringApplication;

public class TestFoundationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(FoundationServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
