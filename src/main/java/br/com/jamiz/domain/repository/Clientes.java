package br.com.jamiz.domain.repository;

import java.lang.Object;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.jamiz.domain.entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; //vem com conexoes configuradas
import org.springframework.jdbc.core.RowMapper; //mapeia o resultado do DB para uma classe
import org.springframework.stereotype.Repository;

@Repository //fazer com que o spring registre essa classe como um repositorio - acessa base de dados
//especificadade -> spring vai enteder que faz alterações na base de dados, todas as exception que ocorrem na base, vao ser traduzidas para você
public class Clientes {

    private static final String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) "; //aqui ocorre o comando SQL para colocar o cliente na tabela
    private static final String LIST_ALL = "SELECT * FROM CLIENTE";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Clientes(JdbcTemplate jdbcTemplate) { //usando construtor para poder fazer a injeção de dependencia
        this.jdbcTemplate = jdbcTemplate;
    }

    public Cliente salvarCliente(Cliente cliente){
         jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()} );
         return cliente;
         //chama-se esta funcao para poder colocar o cliente dentro da tabela, usando a lib, jdbcTeamplate.update
        // na qual é passado a string do insert e o objeto que pegamos o nome da entidade "Cliente"

    }

    public List<Cliente> obterClientes(){
        //funcao ira fazer o select, depois vai mapear as colunas para a entidade "Cliente"
        return jdbcTemplate.query(LIST_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome"); //pegar o valor String de uma coluna
                return new Cliente(nome, id);
                //ResultSet - todos os resultados que ve la do DB
                //Row
            }
        });
    }
}
