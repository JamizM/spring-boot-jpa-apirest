package br.com.jamiz.rest.controller;

import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.repository.Clientes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("{id}")
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
}
