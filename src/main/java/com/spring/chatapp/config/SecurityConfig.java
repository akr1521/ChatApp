package com.spring.chatapp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")

    private String password;


    @Value("${server.servlet.context-path}")
    private String webBaseUrl;

    @Autowired
    private ApplicationContext context;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf( csrf -> csrf.disable()).authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest.anyRequest().permitAll()
                )
                .build();
    }

    /*
    As per requirement , credentials are hardcoded
     */
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername(username)
                .password(password)
                .roles("USER")
                .build();
            return new InMemoryUserDetailsManager(user);
    }


}



