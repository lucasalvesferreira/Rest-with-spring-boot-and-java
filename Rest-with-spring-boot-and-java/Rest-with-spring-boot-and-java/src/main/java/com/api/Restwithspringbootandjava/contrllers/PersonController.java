package com.api.Restwithspringbootandjava.contrllers;

import com.api.Restwithspringbootandjava.data.dtos.v1.PersonDto;
import com.api.Restwithspringbootandjava.data.dtos.v2.PersonDtoV2;
import com.api.Restwithspringbootandjava.service.PersonService;
import com.api.Restwithspringbootandjava.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {


    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public PersonDto findById(@PathVariable(value = "id") long id) throws Exception {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public List<PersonDto> findAll() throws Exception {
        return service.findAll();
    }

    @PostMapping(
            consumes ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            produces ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public PersonDto create(@RequestBody PersonDto person) throws Exception {
        return service.create(person);
    }
    @PostMapping(value = "/v2",
            consumes ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            produces ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public PersonDtoV2 createv2(@RequestBody PersonDtoV2 person) throws Exception {
        return service.createV2(person);
    }

    @PutMapping(
            consumes ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            produces ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    public PersonDto update(@RequestBody PersonDto person) throws Exception {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
