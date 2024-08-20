package br.com.jamiz.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//JPA entende que todas as propriedades da classe definida como @Entity, sao colunas na base de dados
@Entity//necessita de uma PK para funcionar
public class Cliente {

    @Id //define qual a primery key da entidade, na qual é o proprio "id"
    @GeneratedValue(strategy = GenerationType.AUTO)
    //Essa anotação é usada para indicar como a chave primária (ID) da entidade deve ser gerada
    //automaticamente quando uma nova instância da entidade é persistida no banco de dados.
    private Integer id;

    @NotEmpty(message = "{campo.nome.obrigatorio}") //quando fazermos a validação de um objeto, observa as annotations, e verifica se ela esta vazia
    private String nome;

    @NotEmpty(message = "{campo.cpf.obrigatorio}") //mensagem mandada quando nao preencher o CPF
    @CPF(message = "{campo.cpf.invalido}") //mensagem quando o CPF nao for valido
    @Column(name = "cpf", length = 11)
    private String cpf;

    public Cliente(String nome, Integer id) { //construtores que recebem o ID e Nome
        this.nome = nome;
        this.id = id;
    }

    @JsonIgnore //faz com que nao apareca no PostMan
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) //esta entidade no DB, nao tem uma chave para pedidos,
    //FetchType.EAGER -> ira trazer todos os relatiorios de pedidos isso irá deixar consulta bem pesada
    private Set<Pedido> pedidos; //colecao de objetos da clases pedido
    //Set<> -> demonstra que não deve ter elementos duplicados

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
