package br.com.jamiz.security.jwt;

import br.com.jamiz.Service.Impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    private UserServiceImpl userService;

    public JwtAuthFilter(JwtService jwtServioce, UserServiceImpl userService) {
        this.jwtService = jwtServioce;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1]; //esta funcao ira tirar a palavra "Bearer" e separar com o token, pois caso tenha, iria dar conflito
            boolean isValid = jwtService.tokenValido(token);
            if(isValid){
                String loginUsuario = jwtService.obterUsuario(token);
                UserDetails usuario = userService.loadUserByUsername(loginUsuario);
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }
        filterChain.doFilter(request, response);
    }
    //classe intercepta uma requisicao, e esta mandando uma
}
