package br.com.jamiz.rest.controller;

import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.repository.Clientes;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes") //toda requisicao que for feita para "/api/clientes" caíra neste controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    //    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET) //diz que retorna um método GET
    @ResponseBody //colocando esta annotation para dizer que o "return" é o corpo da minha resposta
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){ //definindo que vai receber variavel via URL
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build(); //usa-se o biuld() quando nao há nada parametrizado
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()){
            clientes.delete(cliente.get()); //usado o delete() pois a classe instancia da classe "Clientes"
            //tem o Jpa que ja vem método juntos com que
        }
        return ResponseEntity.noContent().build();
        //usado o noContent quando nao vamos returnar nada no corpo da requisicao
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente){

        return clientes.findById(id).map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente); //atualiza dentro do banco
            return ResponseEntity.noContent().build();
        })
                .orElseGet( () -> ResponseEntity.notFound().build()); // caso seja buscado o cliente dentro do banco
        //e nao há o cliente, ele devolve um notFound
        //map() se caso existir o resultado, entra dentro do método map
    }

    @GetMapping
    public ResponseEntity find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clientes.findAll(example); //fazendo pesquisa
        //vai pegar as propriedades populadas do objeto "filtro" e
        return ResponseEntity.ok(lista);
    }
}
