package com.example.MaestroContabilidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaestroContabilidadeApplication {
	public static void main(String[] args) {
		SpringApplication.run(MaestroContabilidadeApplication.class, args);
	}

}
