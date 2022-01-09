package com.example.MetroStationProject.config;

import com.example.MetroStationProject.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                /*
                 * H2 Console permitted
                 */
                .antMatchers("/h2/**").permitAll()

                /*
                 * UserController authorization configuration
                 */
                .antMatchers(HttpMethod.POST, "/users").permitAll()

                /*
                 * ProductController authorization & authority configuration
                 */
                .antMatchers(HttpMethod.GET, "/products/**").hasAnyAuthority("SELLER", "BUYER")
                .antMatchers("/products/**").hasAuthority("SELLER")

                /*
                 * VendingMachineController authorization & authority configuration
                 */
                .antMatchers(HttpMethod.POST, "/deposit").hasAuthority("BUYER")
                .antMatchers(HttpMethod.POST, "/buy").hasAuthority("BUYER")
                .antMatchers(HttpMethod.POST, "/reset").hasAuthority("BUYER")

                /*
                 * Common security configurations
                 * Since it is an example application, basic auth is used for simplicity. (JWT or OAuth2 not implemented)
                 */
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .realmName("Vending Machine API")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin();
    }
}

