package com.userflow.user_service.Security;

import com.userflow.user_service.Config.WebSecurityConfig;
import com.userflow.user_service.Service.MyUserDetails;
import com.userflow.user_service.Service.UserService;
import io.jsonwebtoken.Header;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    MyUserDetails myUserDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String authHeader = request.getHeader("Authorization");
         if(authHeader == null || !authHeader.startsWith("Bearer "))
         {
             filterChain.doFilter(request,response);
             return;
         }
         String jwt = authHeader.substring(7);
        String username = jwtUtil.extractUserName(jwt);
         if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
         {
             UserDetails userDetails = myUserDetails.loadUserByUsername(username);
             if(userDetails!=null && jwtUtil.validateToken(jwt,userDetails.getUsername()))
             {
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                     username , userDetails.getPassword() , userDetails.getAuthorities()
                 );
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
             }
         }
         filterChain.doFilter(request,response);
    }
}
