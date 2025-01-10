package br.xksoberbado.apiproducer;

import org.springframework.boot.SpringApplication;

public class TestApiProducerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiProducerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
