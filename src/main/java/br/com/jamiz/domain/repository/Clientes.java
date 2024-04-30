package br.com.jamiz.domain.repository;

import java.lang.Object;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.jamiz.domain.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; //vem com conexoes configuradas
import org.springframework.jdbc.core.RowMapper; //mapeia o resultado do DB para uma classe
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //fazer com que o spring registre essa classe como um repositorio - acessa base de dados
//especificadade -> spring vai enteder que faz alterações na base de dados, todas as exception que ocorrem na base, vao ser traduzidas para você
public class Clientes {

    //private static final String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) "; //aqui ocorre o comando SQL para colocar o cliente na tabela
    private static final String LIST_ALL = "SELECT * FROM CLIENTE ";

//    private final JdbcTemplate jdbcTemplate;

//    @Autowired
//    public Clientes(JdbcTemplate jdbcTemplate) { //usando construtor para poder fazer a injeção de dependencia
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Autowired
    private EntityManager entityManager; //EntityManager, interface que faz todas as operações na base com as entidades
    //ou seja, se formos no método salvarCliente(), ao inves de usarmos a jdbc, usamos o EntityManager
    //ele vai reconhecer cada uma dessas colunas e vai persistir/salvar o cliente

    @Transactional //entityManager precisa de uma transacao para fazer as operacoes
    //necessario colocar o @Transactional pois vaoi gerar ma transacao na base de dados
    public Cliente salvarCliente(Cliente cliente){
         entityManager.persist(cliente);
         return cliente;
         //chama-se esta funcao para poder colocar o cliente dentro da tabela, usando a lib, jdbcTeamplate.update
        //depois de entrar no paramatro do método, ele fica no estado "Manager" = iniciada/gerenciada
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente); //merge() utilizado para atualizar uma entidade
        return cliente;
    }

    @Transactional
    public void deletar(Integer id){
        entityManager.remove(id);
//        Cliente cliente = entityManager.find(Cliente.class, id);
//        deletar(cliente);
        //quando quiser obter entidade pela PK, basta usar find(classe, identificador)
        //se caso nao der certo o entityManager.remove(id); substituir
    }

    @Transactional
    public void deletar(Cliente cliente){
        if(!entityManager.contains(cliente)){
            entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional(readOnly = true) //transacao é apenas leitura
    public List<Cliente> buscarPorNome(String nome){
        String jpql = " SELECT C FROM Cliente C WHERE C.nome LIKE :NOME ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter(nome, "%" + nome + "%");
        return query.getResultList();
    }

    public List<Cliente> obterClientes(){
        //funcao ira fazer o select, depois vai mapear as colunas para a entidade "Cliente"
        return entityManager.createQuery("from Cliente ", Cliente.class)
                .getResultList();
    }

    public RowMapper<Cliente> obterClienteMapper(){
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome"); //pegar o valor String de uma coluna
                return new Cliente(nome, id);
                //ResultSet - todos os resultados que ve la do DB
                //Row
            }
        };
    }
}
