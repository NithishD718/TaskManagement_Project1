package com.userflow.user_service.Service;

import com.userflow.user_service.Entity.UserDetail;
import com.userflow.user_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetails implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail userDetails = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),userDetails.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(userDetails.getRole())));
    }
}
