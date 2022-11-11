package com.api.Restwithspringbootandjava.service;

import com.api.Restwithspringbootandjava.contrllers.PersonController;
import com.api.Restwithspringbootandjava.data.dtos.v1.PersonDto;
import com.api.Restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.api.Restwithspringbootandjava.mapper.DozerMapper;
import com.api.Restwithspringbootandjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name" + username + "!");
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " nao encontrado!");
        }
    }

    public PersonDto findById(Long id) throws Exception {


        logger.info("Finding one person!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));
        var dto = DozerMapper.parseObject(entity, PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

}
