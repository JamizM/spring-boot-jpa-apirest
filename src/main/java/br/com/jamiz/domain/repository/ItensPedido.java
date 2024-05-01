package br.com.jamiz.domain.repository;

import br.com.jamiz.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {

}
