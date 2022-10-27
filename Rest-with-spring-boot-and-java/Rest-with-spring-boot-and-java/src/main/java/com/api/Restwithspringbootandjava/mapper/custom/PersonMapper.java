package com.api.Restwithspringbootandjava.mapper.custom;

import com.api.Restwithspringbootandjava.data.dtos.v1.PersonDto;
import com.api.Restwithspringbootandjava.data.dtos.v2.PersonDtoV2;
import com.api.Restwithspringbootandjava.model.PersonModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDtoV2 convertEntityToDto(PersonModel person){

        PersonDtoV2 dto = new PersonDtoV2();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        dto.setBirthDay(new Date());
        return dto;


    }
    public PersonModel convertDtoToEntity(PersonDtoV2 person){

        PersonModel entity = new PersonModel();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;


    }

}
