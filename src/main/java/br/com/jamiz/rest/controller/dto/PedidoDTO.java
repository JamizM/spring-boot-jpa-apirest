package br.com.jamiz.rest.controller.dto;


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
    @NotNull(message = "Informe  código do cliente")
    private Integer cliente;
    @NotNull(message = "Campo total do pedido é obrigatorio")
    private BigDecimal total;
    //criar annotation customizada pis é uma lista
    private List<ItemPedidoDTO> itens;
}
