package br.com.jamiz.domain.entity;

public class Cliente {
    private Integer id;
    private String nome;

    public Cliente(){

    }

    public Cliente(String nome, Integer id) { //construtores que recebem o ID e Nome
        this.nome = nome;
        this.id = id;
    }

    public Cliente (String nome){
        this.nome = nome;
    }

    public Object getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
