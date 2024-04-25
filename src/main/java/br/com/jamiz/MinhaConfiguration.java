package br.com.jamiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//Caso o nome da classe seja a mesma do "annotation" ira dar conflito e nao ira importar

@Configuration
@Profile("development") //so ira rodar no ambiente de desenvolvimento
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Rodando config");
        };
    }
}
