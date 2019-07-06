package com.sda.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/loggedusers")
                .and()
                .logout().logoutSuccessUrl("/index");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(bcryptPasswordEncoder.encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(bcryptPasswordEncoder.encode("password"))
                .roles("ADMIN").and().passwordEncoder(bcryptPasswordEncoder);
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select u.login, u.password,1  from user u where u.login=?")
                .authoritiesByUsernameQuery("select u.login, u.role, 1 from user u where u.login=?")
                .dataSource(jdbcTemplate.getDataSource()).passwordEncoder(bcryptPasswordEncoder);
    }

}


