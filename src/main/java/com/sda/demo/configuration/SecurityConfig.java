package com.sda.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users")
                .hasAnyAuthority("ROLE_USER")
                .antMatchers("/adduser")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
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
                .defaultSuccessUrl("/index")
                .and()
                .logout().logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("password")
                .roles("ADMIN");
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select u.username, u.password,1 from application_user u where u.username=?")
                .authoritiesByUsernameQuery("select u.username, u.role,1 from application_user u where u.username=?")
                .dataSource(jdbcTemplate.getDataSource());
    }

}


