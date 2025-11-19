package com.finanbot;

import org.springframework.boot.SpringApplication;

public class TestFinanbotApplication {

	public static void main(String[] args) {
		SpringApplication.from(FinanbotApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
