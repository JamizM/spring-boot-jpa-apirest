package br.com.jamiz.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
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

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;
}
