package br.com.jamiz.rest.controller;

import br.com.jamiz.Exception.SenhaInvalidaException;
import br.com.jamiz.Service.Impl.UserServiceImpl;
import br.com.jamiz.domain.entity.Usuario;
import br.com.jamiz.rest.controller.dto.CredenciaisDTO;
import br.com.jamiz.rest.controller.dto.TokenDTO;
import br.com.jamiz.security.jwt.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios") //endpoints para cadastrar os usuarios (substituir o /login do security)
@RequiredArgsConstructor
public class UsuarioController {

    private final UserServiceImpl usuariosService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuariosService.salvar(usuario);
    }

    @PostMapping ("/auth")//usar post para enviar informacoes, mais seguro
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try {
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuariosService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);

            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

        }


    }
}
