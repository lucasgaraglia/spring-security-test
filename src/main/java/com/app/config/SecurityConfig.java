package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// https://www.youtube.com/watch?v=IPWBQDMIYkc&ab_channel=UnProgramadorNace

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {



//    COMPONENTE 1. FILTROS
//    httpSecurity es un objeto que se pasa por todos los filtros
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.build();
    }

    // COMPONENTE 2. AUTHENTICATION MANAGER. SE CONECTA CON LOS PROVIDERS
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    COMPONENTE 3. AUTHENTICATION PROVIDER, TIENE DOS COMPONENTES:
//    - PasswordEncoder (encripta y compara contrasenias)
//    - UserDetailsServide (se conecta con la bdd y trae users)
    @Bean
    public AuthenticationProvider authenticationProvider(){
        // hay muchos tipos de providers, el DaoAuth se conecta a la bdd y trae los users
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(null);
        provider.setUserDetailsService(null);
        return provider;
    }

//    COMPONENTE 4. PASSWORD ENCODER, DENTRO DE AUTH PROVIDER
    @Bean
    public PasswordEncoder passwordEncoder(){
//        NoOpPass es un encoder que no encripta. se usa solo para pruebas
//        se usa BCrypt en produccion
        return NoOpPasswordEncoder.getInstance();
    }
}
