package com.api.Restwithspringbootandjava.unittests.mapper;

import com.api.Restwithspringbootandjava.data.dtos.v1.PersonDto;
import com.api.Restwithspringbootandjava.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {


    public PersonModel mockEntity() {
        return mockEntity(0);
    }

    public PersonDto mockVO() {
        return mockVO(0);
    }

    public List<PersonModel> mockEntityList() {
        List<PersonModel> persons = new ArrayList<PersonModel>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDto> mockVOList() {
        List<PersonDto> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    public PersonModel mockEntity(Integer number) {
        PersonModel person = new PersonModel();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonDto mockVO(Integer number) {
        PersonDto person = new PersonDto();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }


}
