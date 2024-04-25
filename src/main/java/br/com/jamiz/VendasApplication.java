package br.com.jamiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication //reconhecer que esta classe vai rodar uma aplicação springboot
//no Spring boot application, ele ja possui Configuration @EnableAutoConfiguration @ComponentScan implicito
//desta forma nao precisa passar para scannears
@RestController//mandar mensagens atraves do browser
public class VendasApplication {

    @Autowired
    @Qualifier("applicationName")
    //pega o objeto "aplicationName" da classe "MinhaConfiguracao" e
    private String applicationName;

    @GetMapping("/hello") //acessando o endpoint "/hello"
    public String helloWorld(){
        return applicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}