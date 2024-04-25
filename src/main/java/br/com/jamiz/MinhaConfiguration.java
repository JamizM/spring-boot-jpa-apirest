package br.com.jamiz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Caso o nome da classe seja a mesma do "annotation" ira dar conflito e nao ira importar

@Configuration
public class MinhaConfiguration {

    @Bean(name = "applicationName") //crie um objeto no contexto da aplicação, para usar aonde precisar
    public String applicationName(){
        return "Sistema de Vendas";
    }
}
