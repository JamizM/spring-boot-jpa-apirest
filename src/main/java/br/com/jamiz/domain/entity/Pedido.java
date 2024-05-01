package br.com.jamiz.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //mapeando uma FK (Foreign Key), pois na linha 14 do .sql, temos o client_id referenciando a tabela de cliente
    @ManyToOne //muitos para um
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    //JPA ja reconhece o tipo de propriedade LocalDate
    private LocalDate dataPedido;

    @Column(precision = 20, scale = 2) //como na tabela "total" temos BigDecimal, que é um ponto flutuate (float)
    //colocamos o tamanho length=20, que é seu tamanho, e duas casas decimais (precision=2)
    private BigDecimal total;

    @OneToMany
    private List<ItemPedido> itens; //referencia com a tabela ItemPedido

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                '}';
    }
}
