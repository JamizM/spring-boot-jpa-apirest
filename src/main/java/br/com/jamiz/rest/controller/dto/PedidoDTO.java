package br.com.jamiz.rest.controller.dto;


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
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
