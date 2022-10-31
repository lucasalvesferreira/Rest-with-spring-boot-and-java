package com.api.Restwithspringbootandjava.service;

import com.api.Restwithspringbootandjava.contrllers.PersonController;
import com.api.Restwithspringbootandjava.data.dtos.v1.PersonDto;
import com.api.Restwithspringbootandjava.exceptions.RequiredObjectIsNullException;
import com.api.Restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.api.Restwithspringbootandjava.mapper.DozerMapper;
import com.api.Restwithspringbootandjava.mapper.custom.PersonMapper;
import com.api.Restwithspringbootandjava.model.PersonModel;
import com.api.Restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public List<PersonDto> findAll() {

        logger.info("Finding all people!");
        var persons= DozerMapper.parseListObjects(repository.findAll(),PersonDto.class);
        persons
             .stream()
             .forEach(p -> {
                 try {
                     p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             });
        return persons;
    }

    public PersonDto findById(Long id) throws Exception {

        logger.info("Finding one person!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));
        var dto = DozerMapper.parseObject(entity,PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public PersonDto create(PersonDto person) throws Exception {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Create one person!");
        var entity = DozerMapper.parseObject(person, PersonModel.class);
        var dto= DozerMapper.parseObject(repository.save(entity),PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;

    }

//    public PersonDtoV2 createV2(PersonDtoV2 person){
//
//        logger.info("Create one person with v2!");
//        var entity = mapper.convertDtoToEntity(person);
//
//        var dto=mapper.convertEntityToDto(repository.save(entity));
//        return dto;
//    }
    public  PersonDto update(PersonDto person) throws Exception {

        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("updating one person!");
        var entity = repository.findById(person.getKey()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto= DozerMapper.parseObject(repository.save(entity),PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;

    }
    public  void delete(Long id){

        logger.info("Deleting one person!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));

        repository.delete(entity);
    }


}
