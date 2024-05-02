package br.com.jamiz.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes") //toda requisicao que for feita para "/api/clientes" caíra neste controller
public class ClienteController {

    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET) //diz que retorna um método GET
    @ResponseBody //colocando esta annotation para dizer que o "return" é o corpo da minha resposta
    public String helloClientes(@PathVariable("nome") String nomeCliente){ //definindo que vai receber variavel via URL
        return String.format("hello " + nomeCliente);
    }
}
