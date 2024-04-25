package br.com.jamiz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication //reconhecer que esta classe vai rodar uma aplicação springboot
@RestController//mandar mensagens atraves do browser
public class VendasApplication {

    @GetMapping("/hello") //acessando o endpoint "/hello"
        public String helloWorld(){
            return "hello world";
        }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}