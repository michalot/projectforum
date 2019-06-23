package com.sda.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity    //zabezpieczanie aplikacji
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/deleteuser")
                .hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/addapplicationuser")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/users")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_READ_ONLY")  // tylko admin może poddejrzec wszystkich użytkowiników
                .antMatchers("/adduser")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/index/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_READ_ONLY")
                .anyRequest().permitAll()
                .and().csrf().disable()               //blokuje przechwyywanie sesji po zamknieciu przegladarki ale nie wylogowaniu sie
                .headers().frameOptions().disable()
                .and().formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password").loginProcessingUrl("/login-process")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index").and()
                .logout().logoutSuccessUrl("/login");
    }

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
                .usersByUsernameQuery("SELECT u.username, u.password, 1 from application_user u where u.username=?")
                .authoritiesByUsernameQuery("SELECT u.username, u.role, 1 from application_user u where u.username=?")
                .dataSource(jdbcTemplate.getDataSource());
    }
}