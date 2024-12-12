package com.example.cliente.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN")
                ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactiva CSRF
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll() // Permite acceso sin autenticación
                .antMatchers("/cliente/listar").authenticated() // Requiere autenticación para esta ruta
                .antMatchers("/cliente/guardar").authenticated()
                .antMatchers("/cliente/actualizar/").authenticated()
                .antMatchers("/cliente/eliminar/").authenticated()
                .antMatchers("/cliente/buscar/").authenticated()
                .and()
                .httpBasic(); // Usar autenticación básica
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar encriptación para las contraseñas
    }
}
