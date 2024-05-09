package br.com.jamiz.Service.Impl;

import br.com.jamiz.Exception.RegraNegocioExcpetion;
import br.com.jamiz.Service.PedidoService;
import br.com.jamiz.domain.entity.Cliente;
import br.com.jamiz.domain.entity.ItemPedido;
import br.com.jamiz.domain.entity.Pedido;
import br.com.jamiz.domain.entity.Produto;
import br.com.jamiz.domain.repository.Clientes;
import br.com.jamiz.domain.repository.ItensPedido;
import br.com.jamiz.domain.repository.Pedidos;
import br.com.jamiz.domain.repository.Produtos;
import br.com.jamiz.rest.controller.dto.ItemPedidoDTO;
import br.com.jamiz.rest.controller.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor //gerado construtor com todos os argumentos obrigatorios, os quais tem "final"
@Service
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidos;
    private final Clientes clientes;
    private final Produtos produtos;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientes
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioExcpetion("Codigo cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidos.save(pedido);
        itensPedidoRepository.saveAll((itensPedido));
        pedido.setItens(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidos.findByIdFetchItens(id);
    }

    //converter itens em itempedido
    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioExcpetion("Nao é possivel realizar um pedido sem itens");
        }
        return itens
        .stream()
        .map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtos
                    .findById(idProduto)
                    .orElseThrow(
                            () -> new RegraNegocioExcpetion("Codigo cliente invalido" + idProduto));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
