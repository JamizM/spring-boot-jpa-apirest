package br.com.jamiz.rest.controller;

import br.com.jamiz.Service.Impl.UserServiceImpl;
import br.com.jamiz.domain.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios") //endpoints para cadastrar os usuarios (substituir o /login do security)
@RequiredArgsConstructor
public class UsuarioController {

    private final UserServiceImpl usuariosService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuariosService.salvar(usuario);
    }
}
