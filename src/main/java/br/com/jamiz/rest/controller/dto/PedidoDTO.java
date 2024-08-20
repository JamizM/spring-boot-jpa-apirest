package br.com.jamiz.rest.controller.dto;


import br.com.jamiz.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    //DTO - Data Transfer Object
    //mapear objeto com propriedades simples
    //recebe via requisicao e cria o modelo de dados

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;

    @NotNull(message = "{campo.total-pedidos.obrigatorio}")
    private BigDecimal total;

    //criar annotation customizada pis é uma lista
    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}") //anottation customizada
    private List<ItemPedidoDTO> itens;
}
