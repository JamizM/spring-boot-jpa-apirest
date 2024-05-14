package br.com.jamiz.rest.controller;

import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.repository.Clientes;

import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes") //toda requisicao que for feita para "/api/clientes" caíra neste controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    //    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET) //diz que retorna um método GET
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){ //definindo que vai receber variavel via URL
        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //código de retorno para cliente - 201
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return clientes.save(cliente);
        //@Valid -> Spring irá validar esse objeto usando as anotações de validação antes de chamar o método save.
        // Se qualquer uma das validações falhar, uma resposta de erro será retornada, informando quais
        // campos não passaram na validação e por quê.
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void delete(@PathVariable Integer id){
        clientes.findById(id)
                .map(cliente -> {clientes.delete(cliente);
                return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        clientes
                .findById(id)
                .map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente); //atualiza dentro do banco
            return cliente;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado")); // caso seja buscado o cliente dentro do banco
        //e nao há o cliente, ele devolve um notFound
        //map() se caso existir o resultado, entra dentro do método map
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example); //fazendo pesquisa
        //vai pegar as propriedades populadas do objeto "filtro" e
    }
}
