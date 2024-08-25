package br.com.jamiz.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {
    //entidade para cadastro dos usuarios, se é possivel entrar em certos acessos ou nao
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "{campo.login.obrigatorio}")
    @Column
    private String login;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column
    private String senha;

    @Column
    private boolean admin;
}
