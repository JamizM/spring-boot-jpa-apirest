package br.com.jamiz.Service;

import br.com.jamiz.domain.entity.Pedido;
import br.com.jamiz.domain.entity.enums.StatusPedido;
import br.com.jamiz.rest.controller.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    default void atualizaStatus(Integer id, StatusPedido status){

    }

}
