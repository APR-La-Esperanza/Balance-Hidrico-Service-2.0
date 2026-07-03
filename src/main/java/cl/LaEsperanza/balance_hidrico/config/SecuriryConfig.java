package cl.LaEsperanza.balance_hidrico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuriryConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para permitir peticiones POST sin bloqueos
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite el acceso total a Swagger y a tus controladores
            );
        return http.build();
    }
}