package br.com.jamiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


//Caso o nome da classe seja a mesma do "annotation" ira dar conflito e nao ira importar

@Development //usando esta classe personalizada, ira ter a função de herdar as duas anotações
//@profile e Configuration
//sem precisar escrever @Configuration e @Profile
public class MinhaConfiguration {

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Rodando config");
        };
    }
}
