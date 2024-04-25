package br.com.jamiz;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //ser aplicada somente a elementos de tipo TYPE, ou seja, a classes, interfaces, enums ou annotations.
@Retention(RetentionPolicy.RUNTIME) //estará disponível durante a execução do programa, permitindo que ela seja lida através de reflection em tempo de execução.
@Configuration //marca uma classe como uma fonte de definições de beans para o container de injeção de dependências do Spring.
@Profile("development") //significa que as configurações ou beans marcados com @Development só devem ser ativados quando o perfil de desenvolvimento (development) estiver ativo.
//toda vez que você mudar o que esta escrito no @Profilo, todas as classes que tiverem esta anotação irao mudar
public @interface Development {

}
