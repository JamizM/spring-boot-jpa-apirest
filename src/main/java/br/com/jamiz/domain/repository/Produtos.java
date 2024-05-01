package br.com.jamiz.domain.repository;

import br.com.jamiz.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
    //dentro do JpaRepository<> -> dentro do operador diamante, colocamos a classe que queremos usar e o tipo do identificador daquela classe, no qual é Integer


}

