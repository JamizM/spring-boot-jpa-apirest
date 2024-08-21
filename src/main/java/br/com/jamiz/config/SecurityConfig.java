package br.com.jamiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.awt.image.MemoryImageSource;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity //
public class SecurityConfig {

    //objeto que criptografa e descriptogrfa a senha de usuario
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder(); //toda vez que usuario passa a senha, é gerado um hash
    }

    //parte de autenticacao



    //construcao de um objeto User
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("user1")
                .password("password1")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("adminPass")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }

    //verificacao se o usuario tem acesso a certas roles dentro do projeto
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }
}

//https://www.youtube.com/watch?v=iWwv9r9inns
