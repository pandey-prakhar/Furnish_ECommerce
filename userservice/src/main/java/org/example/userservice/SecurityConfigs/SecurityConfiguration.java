package org.example.userservice.SecurityConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // Disable CSRF protection
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().permitAll() // Allow all requests
//                );
//        return http.build();
//    }

}
