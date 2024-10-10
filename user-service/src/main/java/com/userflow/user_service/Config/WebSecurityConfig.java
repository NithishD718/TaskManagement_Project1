package com.userflow.user_service.Config;

import com.userflow.user_service.Entity.UserDetail;
import com.userflow.user_service.Security.JwtAuthenticationFilter;
import com.userflow.user_service.Service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    MyUserDetails myUserDetails;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
{
  return httpSecurity.csrf(AbstractHttpConfigurer::disable)
          .sessionManagement(session -> session
                  .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
          .authorizeHttpRequests(registry -> {
              registry.requestMatchers("/user/login","/user/register" ,"/user/authenticate").permitAll();
              registry.anyRequest().authenticated();
          })
          .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
          .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class )
          .build();
}
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager()
    {
        return  new ProviderManager(authenticationProvider());
    }
    public UserDetailsService userDetailsService()
    {
        return myUserDetails;
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetails);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
