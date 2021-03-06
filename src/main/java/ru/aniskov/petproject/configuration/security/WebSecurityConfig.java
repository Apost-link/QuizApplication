package ru.aniskov.petproject.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.aniskov.petproject.pojo.model.Role;

@Configuration
@EnableConfigurationProperties
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(@Qualifier("userServiceImpl") UserDetailsService userDetailsService){
        super();
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/actuator/**").hasAnyAuthority(Role.ADMIN.getValue())
                .and().authorizeRequests().antMatchers("/api/v1/user/**").hasAnyAuthority(Role.ADMIN.getValue())
                .and().authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/quiz/**").hasAnyAuthority(Role.ADMIN.getValue())
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/quiz/**").authenticated()
                .and().authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/sets/**").hasAnyAuthority(Role.ADMIN.getValue())
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/sets/**").authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.userDetailsService(userDetailsService);
    }
}
