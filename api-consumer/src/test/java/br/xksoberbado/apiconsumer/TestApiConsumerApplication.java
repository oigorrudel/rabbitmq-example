package br.xksoberbado.apiconsumer;

import org.springframework.boot.SpringApplication;

public class TestApiConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.from(ApiConsumerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
