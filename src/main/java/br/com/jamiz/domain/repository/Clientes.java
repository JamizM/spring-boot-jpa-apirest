package br.com.jamiz.domain.repository;

import br.com.jamiz.domain.entity.Cliente;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//@Repository //fazer com que o spring registre essa classe como um repositorio - acessa base de dados
//especificadade -> spring vai enteder que faz alterações na base de dados, todas as exception que ocorrem na base, vao ser traduzidas para você
public interface Clientes extends JpaRepository<Cliente, Integer> {
    //dentro do operador diamante ele recebe dois paramentros <Entidade, o Tipo do id>

    //save() - método que salva os clientes e atualiza
    //findAll() - retorna todas as instancias das entidades cobertas pelo @Repository
    //delete() - deleta um usuario

    List<Cliente> findByNomeLike(String nome);
}
