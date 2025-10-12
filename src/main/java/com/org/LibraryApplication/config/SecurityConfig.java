package com.org.LibraryApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
      return httpSecurity.csrf(csrf->csrf.disable())
              .authorizeHttpRequests(auth->auth.
                      requestMatchers("/roles/**","/register/**")
                      .permitAll().anyRequest().authenticated())
              .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)throws Exception{
        return authConfig.getAuthenticationManager();
    }
}
