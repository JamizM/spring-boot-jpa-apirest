package br.com.jamiz;

import br.com.jamiz.domain.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@SpringBootApplication
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracaoToken;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario){
        long expString = Long.parseLong(expiracaoToken);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString); //pegando hora atual e somando alguns minutos (salting)
        Instant instant =  dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant(); //pega regiao do brasil
        Date data = Date.from(instant); //para Instante transforma em Date


        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact(); //retorna string
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService jwtService = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("user").build();
        String token = jwtService.gerarToken(usuario);
        System.out.println(token);
    }
}
