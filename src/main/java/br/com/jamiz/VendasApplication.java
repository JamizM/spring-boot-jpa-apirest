package br.com.jamiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication //reconhecer que esta classe vai rodar uma aplicação springboot
//no Spring boot application, ele ja possui Configuration @EnableAutoConfiguration @ComponentScan implicito
//desta forma nao precisa passar para scannears
@RestController//mandar mensagens atraves do browser
public class VendasApplication {

    @Value("${application.name}") //aplicando interpolção - vai procurar a propriedade escrita entre aspas, e vai pegar o valor que esta la e vai injetar la dentro
    //pega o objeto "aplicationName" da classe "MinhaConfiguracao" e
    private String applicationName;

    @Autowired
    @Qualifier("gato")
    private Animal animal;

    @Bean
    public CommandLineRunner executar(){
        return args -> {
          this.animal.fazerBarulho();
        };
    }

    @GetMapping("/hello") //acessando o endpoint "/hello"
    public String helloWorld(){
        return applicationName;
    }
    //como dentro de application.properties esta rodando na raiz do projeto /sistemas-vendas, ira rodar nesta
    //pasta principal, porem cdesta forma -> /sistema-vendas/hello

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}