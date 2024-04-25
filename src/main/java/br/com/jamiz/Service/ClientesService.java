package br.com.jamiz.Service;

import br.com.jamiz.Model.Cliente; //classe importada
import br.com.jamiz.Repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService { //ClientesService depende do ClientesRepository, poi se nao tiver, como ira "persitir" o cliente

    private ClientesRepository repository;

    @Autowired //spring container(Injecao de Dependencia) -> scannear a classe, criar isntancia da classe, ira buscar uma instancia
    public ClientesService(ClientesRepository repository){ // construtor parametrizado
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){
        //aplicar validações
    }
}
