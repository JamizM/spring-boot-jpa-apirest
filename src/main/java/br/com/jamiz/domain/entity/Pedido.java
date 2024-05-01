package br.com.jamiz.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //mapeando uma FK (Foreign Key), pois na linha 14 do .sql, temos o client_id referenciando a tabela de cliente
    @ManyToOne //muitos para um
    @JoinColumn(name = "cliente_id")
    private Cliente client;

    @Column(name = "data_pedido")
    //JPA ja reconhece o tipo de propriedade LocalDate
    private LocalDate dataPedido;

    @Column(name = "total", length = 20, precision = 2) //como na tabela "total" temos BigDecila, que é um ponto flutuate (float)
    //colocamos o tamanho length=20, que é seu tamanho, e duas casas decimais (precision=2)
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
