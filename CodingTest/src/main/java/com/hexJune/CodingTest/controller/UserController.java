package com.hexJune.CodingTest.controller;

import com.hexJune.CodingTest.model.User;
import com.hexJune.CodingTest.service.CustomUserDetailService;
import com.hexJune.CodingTest.service.UserService;
import com.hexJune.CodingTest.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public User signUp(@RequestBody User user){
        return userService.signUp(user);
    }

    @GetMapping("/token")
    public ResponseEntity<?> getToken(Principal principal){
        try{
            String token = jwtUtility.createToken(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    @GetMapping("/details")
    public Object getLoggedInUserDetails(Principal principal){
        String username = principal.getName();// logged In username
        return userService.getUserInfo(username);
    }

}
