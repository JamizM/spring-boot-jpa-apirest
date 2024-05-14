package br.com.jamiz.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id //identificar o PK da entidade
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //nao é necessario colocar pois tem o mesmo nome na tabela, porem colocamos para facilitar entendimento
    private Integer id;

    @NotEmpty(message = "campo descricao obrigatoria") //usase o NotEmpty Aqui pois o campo é uma String, pois tambem pode ter uma String vazia
    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "campo preco é obrigatorio") //neste caso é notnull pois o preco é inevitavel
    @Column(name = "preco_unitario")
    private BigDecimal preco;
}
