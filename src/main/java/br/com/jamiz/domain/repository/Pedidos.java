package br.com.jamiz.domain.repository;

import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Pedidos extends JpaRepository<Pedido, Integer> {
    //conseguir pedidos de cliente
    List<Pedido> findByCliente(Cliente cliente);
}
