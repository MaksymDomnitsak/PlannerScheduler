package com.example.plannerscheduler.configuration;

import com.example.plannerscheduler.security.AuthEntryPointJwt;
import com.example.plannerscheduler.security.JwtAuthorizationFilter;
import com.example.plannerscheduler.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    private final JwtAuthorizationFilter filter;

    public SecurityConfiguration(AuthenticationProvider authenticationProvider, JwtAuthorizationFilter filter) {
        this.authenticationProvider = authenticationProvider;
        this.filter = filter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/subject/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/group/**").permitAll()
            .requestMatchers(HttpMethod.GET,"/api/teacher/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/schedule").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/schedule/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/toCalendar/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
