package br.com.jamiz.rest.controller;

import br.com.jamiz.Service.PedidoService;
import br.com.jamiz.domain.entity.ItemPedido;
import br.com.jamiz.domain.entity.Pedido;
import br.com.jamiz.domain.entity.enums.StatusPedido;
import br.com.jamiz.rest.controller.dto.AtualizacaoStatusPedidoDTO;
import br.com.jamiz.rest.controller.dto.InformacoesItemPedidoDTO;
import br.com.jamiz.rest.controller.dto.InformacoesPedidoDTO;
import br.com.jamiz.rest.controller.dto.PedidoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId(); //codigo do pedido, será possivel consultar os dados
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map(p -> conveter(p))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "pedido nao encontrado"));
    }

    @PatchMapping("{id}")//utilizada para atualizar apenas campos especificos do DB
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO conveter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd:MM:yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(conveter((Pedido) pedido.getItens()).getItens())
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens
                .stream()
                .map(
                        item -> InformacoesItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList());
    }

}
