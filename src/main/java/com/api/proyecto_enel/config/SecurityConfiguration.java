package com.api.proyecto_enel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(registry-> {
            registry.requestMatchers("/clientes/**").hasRole("CLIENTE");
            registry.requestMatchers("/admins/**").hasRole("ADMIN");
            registry.requestMatchers("/empresas/**").hasRole("EMPRESAS");
                    registry.anyRequest().authenticated();
        })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

    @Bean
    //LA CLAVE ES 1234
    public UserDetailsService userDetailsService() {
        UserDetails cliente = User.builder()
                .username("juanCarlos")
                .password("$2a$12$MLWMTD0mvI3rEsB553Mey.liWDLa.N4IYDkEcAW5VUZXEkDGrAOMu")
                .roles("CLIENTE")
                .build();
        UserDetails empresa = User.builder()
                .username("empresa")
                .password("$2a$12$MLWMTD0mvI3rEsB553Mey.liWDLa.N4IYDkEcAW5VUZXEkDGrAOMu")
                .roles("EMPRESA")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("$2a$12$MLWMTD0mvI3rEsB553Mey.liWDLa.N4IYDkEcAW5VUZXEkDGrAOMu")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(cliente, admin);

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
