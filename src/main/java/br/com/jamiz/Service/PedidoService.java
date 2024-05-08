package br.com.jamiz.Service;

import br.com.jamiz.domain.entity.Pedido;
import br.com.jamiz.rest.controller.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
