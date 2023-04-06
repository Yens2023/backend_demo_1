package com.project.mycomerce.config.security;

import com.project.mycomerce.config.jwt.JwtAuthEntryPoint;
import com.project.mycomerce.config.jwt.JwtRequestFilter;
import com.project.mycomerce.service.security.Impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .anyRequest().permitAll();
    }

    // CREACION DE BEANS
    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter(){
        return new JwtRequestFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // SOBREESCRIBIR FUNCIONALIDAD SECURITY POR DEFECTO



}
