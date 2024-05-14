package br.com.jamiz.rest.controller;

import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.entity.Produto;
import br.com.jamiz.domain.repository.Produtos;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private Produtos produtos;

    public ProdutoController(Produtos produtos){
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return produtos.save(produto);
    }

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id){
        return produtos
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto){
        produtos
                .findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId()); // aqui ocorre a deleção
                    produtos.save(produto);
                    return produto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtos
                .findById(id)
                .map(produtoExistente -> {
                    produtos.delete(produtoExistente);
                    //A função do .map() é aplicar uma função a cada elemento de uma coleção (como uma lista, um stream ou um Optional) e coletar os resultados em uma nova coleção do mesmo tipo.
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtos.findAll(example);
    }
}
