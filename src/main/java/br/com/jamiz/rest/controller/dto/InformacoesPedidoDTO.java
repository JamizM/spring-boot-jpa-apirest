package br.com.jamiz.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //gera todas as propriedades, um objeto, e vai disponibilizar uma instancia para contruir um objeto desse
public class InformacoesPedidoDTO {
    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private List<InformacoesItemPedidoDTO> itens;
}
