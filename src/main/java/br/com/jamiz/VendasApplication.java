package br.com.jamiz;

import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.repository.Clientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("salvando clientes");

            clientes.salvarCliente(new Cliente("Douglas"));

            clientes.salvarCliente(new Cliente("Pedrin"));

            List<Cliente> todosClientes = clientes.obterClientes();
            todosClientes.forEach(System.out::println);
            //"::" -Ele é usado para referenciar métodos existentes ou construtores de uma forma mais concisa
            //no caso se nao tive que usar o operador ": :", seria adequado usar o lambda "->", ou seja:
            //todosClientes.forEach(s -> System.out.println);
            //porem como nao queremos comer muita bola, usamos este operador

            System.out.println("Atualizando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterClientes();
            todosClientes.forEach(System.out::println);

            clientes.buscarPorNome("CLICLI").forEach(System.out::println);

            //System.out.println("deletando clientes");
            //clientes.obterClientes().forEach(clientes::deletar);

            todosClientes = clientes.obterClientes();
            if(todosClientes.isEmpty()){
                System.out.println("nenhum cliente ta na tabela");
            }
            else{
                todosClientes.forEach((System.out::println));
            }
            todosClientes.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}