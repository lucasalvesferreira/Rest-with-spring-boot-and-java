package com.api.Restwithspringbootandjava.contrllers;

import com.api.Restwithspringbootandjava.model.PersonModel;
import com.api.Restwithspringbootandjava.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {


        @Autowired
        private PersonService service;

        @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public PersonModel findById(@PathVariable (value = "id")String id) throws Exception{
        return service.findById(id);
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public List<PersonModel> findAll() throws Exception{
        return service.findAll();
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE ,
                             produces = MediaType.APPLICATION_JSON_VALUE)
        public PersonModel create(@RequestBody PersonModel person) throws Exception{
        return service.create(person);
        }
        @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE ,
                             produces = MediaType.APPLICATION_JSON_VALUE)
        public PersonModel update(@RequestBody PersonModel person) throws Exception{
        return service.update(person);
        }
        @DeleteMapping(value = "/{id}")
        public void delete(@PathVariable (value = "id")String id) throws Exception{
               service.delete(id);
        }



}
