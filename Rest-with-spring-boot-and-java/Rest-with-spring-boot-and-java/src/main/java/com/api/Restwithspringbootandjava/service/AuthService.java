//package com.api.Restwithspringbootandjava.service;
//
//import com.api.Restwithspringbootandjava.data.security.AccountCredentialsDto;
//import com.api.Restwithspringbootandjava.repositories.UserRepository;
//import com.api.Restwithspringbootandjava.security.jwt.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    public ResponseEntity singin(AccountCredentialsDto data){
//
//        try{
//
//        }catch (Exception e){
//            throw new BadCredentialsException("Invalid username/password supplied!");
//        }
//
//
//
//    }
//
//
//
//
//
//
//}
