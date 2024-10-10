package com.userflow.user_service.Controller;

import com.userflow.user_service.Entity.LoginForm;
import com.userflow.user_service.Entity.UserDetail;
import com.userflow.user_service.Security.JwtUtil;
import com.userflow.user_service.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/get/{userId}")
    public String GetUserEmail(@PathVariable Integer userId)
    {
      return userService.getUser(userId);
    }
    @PostMapping("/register")
    public ResponseEntity<UserDetail> UserRegister(@RequestBody UserDetail user )
    {
        userService.register(user);
        logger.info("User Registered Successfully");
        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public String UserLogin(@RequestBody LoginForm loginForm)
    {
        return userService.login(loginForm.username() , loginForm.password());
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm)
    {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username() , loginForm.password()));
      if(authentication.isAuthenticated())
      {
         return jwtUtil.generateToken(loginForm.username(), "USER");
      }
      else
          throw new UsernameNotFoundException("Invalid Credentials");
    }
}