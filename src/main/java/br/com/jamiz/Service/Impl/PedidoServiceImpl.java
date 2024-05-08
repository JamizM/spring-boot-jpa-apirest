package br.com.jamiz.Service.Impl;

import br.com.jamiz.Service.PedidoService;
import br.com.jamiz.domain.repository.Pedidos;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl extends PedidoService {

    private Pedidos pedidos;

    public PedidoServiceImpl(Pedidos pedidos) {
        this.pedidos = pedidos;
    }
}
