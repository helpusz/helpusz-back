package br.com.helpusz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/ong/register").permitAll()
        .requestMatchers("/ong/login").permitAll()
        .requestMatchers("/volunteer/register").permitAll()
        .requestMatchers("/volunteer/login").permitAll()

        .anyRequest().authenticated())
      .apply(new JwtConfigurer(jwtTokenProvider));

    return httpSecurity.build();
  }

}
