package com.api.Restwithspringbootandjava.service;

import com.api.Restwithspringbootandjava.data.dtos.v1.PersonDto;
import com.api.Restwithspringbootandjava.data.dtos.v2.PersonDtoV2;
import com.api.Restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.api.Restwithspringbootandjava.mapper.DozerMapper;
import com.api.Restwithspringbootandjava.mapper.custom.PersonMapper;
import com.api.Restwithspringbootandjava.model.PersonModel;
import com.api.Restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return DozerMapper.parseListObjects(repository.findAll(),PersonDto.class);

    }

    public PersonDto findById(Long id) {

        logger.info("Finding one person!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));
        return DozerMapper.parseObject(entity,PersonDto.class);

    }

    public  PersonDto create(PersonDto person){

        logger.info("Create one person!");
        var entity = DozerMapper.parseObject(person, PersonModel.class);

        var dto= DozerMapper.parseObject(repository.save(entity),PersonDto.class);
        return dto;
    }
    public PersonDtoV2 createV2(PersonDtoV2 person){

        logger.info("Create one person with v2!");
        var entity = mapper.convertDtoToEntity(person);

        var dto=mapper.convertEntityToDto(repository.save(entity));
        return dto;
    }
    public  PersonDto update(PersonDto person){

        logger.info("updating one person!");
        var entity = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto= DozerMapper.parseObject(repository.save(entity),PersonDto.class);
        return dto;

    }
    public  void delete(Long id){

        logger.info("Deleting one person!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));

        repository.delete(entity);
    }


}
