package br.com.jamiz.Repository;

import br.com.jamiz.Model.Cliente;
import org.springframework.stereotype.Repository;

@Repository // diz que a classe ira acessar a base de dados
public class ClientesRepository {
    public void persistir(Cliente cliente){}
    //acessa a base de dados e salva o cliente
}
