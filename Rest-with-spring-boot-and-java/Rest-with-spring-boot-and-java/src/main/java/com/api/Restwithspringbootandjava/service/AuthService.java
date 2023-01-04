package com.api.Restwithspringbootandjava.service;

import com.api.Restwithspringbootandjava.data.security.AccountCredentialsDto;
import com.api.Restwithspringbootandjava.data.security.TokenDto;
import com.api.Restwithspringbootandjava.repositories.UserRepository;
import com.api.Restwithspringbootandjava.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    private Logger logger = Logger.getLogger(BookService.class.getName());

    @SuppressWarnings("rawtypes")
    public ResponseEntity sigin(AccountCredentialsDto data){
        try{
            var username = data.getUsername();
            var password = data.getPassword();
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            var user = repository.findByUsername(username);

            var tokenResponse = new TokenDto();
            if (user != null){
                tokenResponse = tokenProvider.createAccessToken(username,user.getRoles());
            }else {
                logger.info("Username " +username + "not found!");
                throw new UsernameNotFoundException("Username " +username + "not found!");
            }

            return ResponseEntity.ok(tokenResponse);

        }catch (Exception e){
            logger.info("Invalid username/password supplied!");
            throw new BadCredentialsException("Invalid username/password supplied!");
        }



    }
    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username,String refreshToken){
        var user = repository.findByUsername(username);

        var tokenResponse = new TokenDto();
        if (user != null){
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        }else {
            logger.info("Username " +username + "not found!");
            throw new UsernameNotFoundException("Username " +username + "not found!");
        }
        return ResponseEntity.ok(tokenResponse);

    }




}
