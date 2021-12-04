package com.apress.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class ToDoSecurityConfig extends WebSecurityConfigurerAdapter { //Наследование этого класса — один из способов переопределения настроек безопасности, поскольку позволяет переопределить методы, которые вам действительно нужны

    //Another way to override the user/password (another alternative to the spring.security.* properties
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //создает AuthenticationManager, с помощью которого можно легко осуществлять аутентификацию в оперативной памяти, аутентификацию LDAP, аутентификацию на основе JDBC, добавлять UserDetailsService и различные AutheticationProvider
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("apress") //закомментируйте добавленные в файл application.properties свойства spring.security
                .password(passwordEncoder().encode("springboot2")) //Для более безопасного использования и шифрования/расшифровки пароля
                .roles("ADMIN","USER");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //использует криптостойкую функцию хеширования
        return new BCryptPasswordEncoder();
    }

////    Simple way to override the formLogin page with .httpBasic
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
////                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .httpBasic();
////                and()
////                formLogin();
//    }

    //With Custom Login Page
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .httpBasic();
    }

}
