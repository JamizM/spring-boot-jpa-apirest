package br.com.jamiz;

import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.entity.Produto;
import br.com.jamiz.domain.repository.Clientes;
import br.com.jamiz.domain.repository.Produtos;
import br.com.jamiz.rest.controller.ClienteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}