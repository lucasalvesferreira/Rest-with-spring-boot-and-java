package com.api.Restwithspringbootandjava.service;

import com.api.Restwithspringbootandjava.model.PersonModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());


    public List<PersonModel> findAll() {

        logger.info("Finding all people!");
        List<PersonModel> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            PersonModel person = mockPerson(i);
            persons.add(person);
        }

        return persons;

    }

    public PersonModel findById(String id) {

        logger.info("Finding one person!");
        PersonModel person = new PersonModel();
        person.setId(counter.incrementAndGet());
        person.setFirtName("Lucas");
        person.setLastName("Seila");
        person.setAdress("sei la");
        person.setGender(" num sei");
        return person;


    }

    public  PersonModel create(PersonModel person){

        logger.info("Create one person!");
        return person;

    }
    public  PersonModel update(PersonModel person){

        logger.info("updating one person!");
        return person;

    }
    public  void delete(String id){

        logger.info("Deleting one person!");

    }

    private PersonModel mockPerson(int i) {

        PersonModel person = new PersonModel();
        person.setId(counter.incrementAndGet());
        person.setFirtName("Lucas" +i);
        person.setLastName("Seila"+i);
        person.setAdress("sei la"+i);
        person.setGender(" num sei"+i);
        return person;
    }
}
