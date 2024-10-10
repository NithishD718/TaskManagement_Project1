package com.userflow.user_service.Service;

import com.userflow.user_service.Entity.UserDetail;
import com.userflow.user_service.Repository.UserRepository;
import com.userflow.user_service.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;
    public void register(UserDetail user){
       if(userRepository.findByUsername(user.getUsername()).isPresent())
        {
            throw new RuntimeException("User name is already present.");
        }
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       userRepository.save(user);
    }

    public String login(String userName , String Password) {
        Optional<UserDetail> optionalUser = userRepository.findByUsername((userName));
        if(optionalUser.isPresent())
        {
           UserDetail user =  optionalUser.get();
           if(passwordEncoder.matches(Password , user.getPassword()))
           {
               return jwtUtil.generateToken(userName, Collections.singletonList(user.getRole()).toString());
           }
           else
               throw new RuntimeException("Invalid Username or Password");
        }
        else
            throw  new RuntimeException("Invalid UserName or Password");
    }

    public String getUser(Integer userId) {
        UserDetail userDetail = userRepository.findById(userId).orElse(null);
        return userDetail.getUsername();
    }
}
