package com.scm.secuitycustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.serviceimplementation.SecurityCustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecrityConfig {

    /* 
    user creation and login using java InMemoryUserDetailsManager

    @Bean
    public UserDetailsService userDetailsService(){

        //creating inMemory users
        UserDetails admin = User.withDefaultPasswordEncoder()
                                .username("admin")
                                .password("admin")
                                .roles("ADMIN","USER")
                                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                                .username("user")
                                .password("user")
                                .roles("USER")
                                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
    */

    @Autowired
    private SecurityCustomUserDetailsService securityCustomUserDetailsService;

    @Autowired
    private OAuthAuthenticateSuccessHandler sHandler;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        //user details service ka object lana hai 
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailsService);

        //ek passwork encoder chahiye - Bcrypt
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain dSecurityFilterChain (HttpSecurity http) throws Exception{
        // http.authorizeHttpRequests()
        // .requestMatchers("/user/**").authenticated()
        // .requestMatchers("/**").permitAll()
        // .and().formLogin()
        // .and().httpBasic();

        http.authorizeHttpRequests(request ->{
            request.requestMatchers("/user/**").authenticated();
            request.anyRequest().permitAll();
        });
        

        http.formLogin(lform ->{
            lform.loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .defaultSuccessUrl("/user/profile")
            .failureUrl("/login?error=true")
            .usernameParameter("email")
            .passwordParameter("password")
            ;
        });
        http.csrf(AbstractHttpConfigurer::disable);
         //oauth2 configuration
         http.oauth2Login(login ->{
            login.loginPage("/login");
            login.successHandler(sHandler);
            // login.defaultSuccessUrl("/user/dashboard");
        });

        http.logout(logoutForm ->{
            logoutForm.logoutUrl("/logout")
                      .logoutSuccessUrl("/login?logout=true");
        });

       

        return http.build();
    }
}
