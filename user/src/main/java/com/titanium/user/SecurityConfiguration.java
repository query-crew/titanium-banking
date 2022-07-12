package com.titanium.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //We provide the data source for users using the below config method
    @Bean
    protected InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> user = new ArrayList<>();
        user.add(User.withUsername("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN").build());
        user.add(User.withUsername("chloe").password(passwordEncoder().encode("pass")).roles("USER").build());
        return new InMemoryUserDetailsManager(user);
    }

    //We provide request authorization in the below method
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Below line will allow any authenticated user to access any resource within the system
        http.authorizeRequests().antMatchers("/h2-console/**", "/member").permitAll();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}