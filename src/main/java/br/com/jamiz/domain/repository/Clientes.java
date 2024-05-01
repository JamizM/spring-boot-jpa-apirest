package br.com.jamiz.domain.repository;

import br.com.jamiz.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Repository //fazer com que o spring registre essa classe como um repositorio - acessa base de dados
//especificadade -> spring vai enteder que faz alterações na base de dados, todas as exception que ocorrem na base, vao ser traduzidas para você
public interface Clientes extends JpaRepository<Cliente, Integer> {
    //dentro do operador diamante ele recebe dois paramentros <Entidade, o Tipo do id>
    //@repository, nao está pois por padrão, quando instalamos ele no POm.xml, ele ja entende que é um repository

    //save() - método que salva os clientes e atualiza
    //findAll() - retorna todas as instancias das entidades cobertas pelo @Repository
    //delete() - deleta um usuario

    @Query(value = " SELECT * FROM Cliente C WHERE c.nome LIKE '%:nome%' ", nativeQuery = true)
    List<Cliente> findByNomeLike( @Param("nome") String nome);

    @Query(value = "DELETE FROM Cliente c WHERE c.nome = :nome")
    @Modifying//necessario especificar que nao é uma consulta
    @Transactional
    List<Cliente> deleteByNome(@Param("nome") String nome);



}
