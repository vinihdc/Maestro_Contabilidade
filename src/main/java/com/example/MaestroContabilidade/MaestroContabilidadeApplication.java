package com.example.MaestroContabilidade;

import Model.BancoDeDados.ColetaDeDadosGraficoBD;
import Model.Entidade.Graficos;
import com.sun.tools.javac.Main;
import lombok.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.*;

@SpringBootApplication
@EnableScheduling
public class MaestroContabilidadeApplication {
	public static void main(String[] args) {
		SpringApplication.run(MaestroContabilidadeApplication.class, args);
	}

}
