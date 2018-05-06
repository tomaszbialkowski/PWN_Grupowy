package com.pwn.pwngrup.configurations;


import com.pwn.pwngrup.service.GrupUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter { //klasa konfiguracyjna

    @Autowired
    private GrupUserDetailsService grupUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/").permitAll()

                //login configuration
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("app_username")
                .passwordParameter("app_password")
                .defaultSuccessUrl("/")//wiazanie, gdzie chcemy przejsc jak się zalogujemy i dane beda prawidlow

                //logout configuration
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")

                //exception handling configuration
                .and().exceptionHandling()
                .accessDeniedPage("/error-view");
    }

    @Autowired //pomocnicza klasa do szyfrowania haseł i weryfikowania czy użytkownik istnieje w baszie
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(grupUserDetailsService).passwordEncoder(passwordEncoder);
        //grup musi być na obiekcie
    }
}


