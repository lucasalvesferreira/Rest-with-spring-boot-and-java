package com.api.Restwithspringbootandjava.contrllers;

import com.api.Restwithspringbootandjava.data.dtos.v1.PersonDto;
import com.api.Restwithspringbootandjava.data.dtos.v2.PersonDtoV2;
import com.api.Restwithspringbootandjava.service.PersonService;
import com.api.Restwithspringbootandjava.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People",description = "Endpoints for Managing People")
public class PersonController {


    @Autowired
    private PersonService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "Find all people ",description = "Find all people ",
    tags = {"People"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(
                            mediaType =  "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public List<PersonDto> findAll() throws Exception {
        return service.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Finds a Person ",description = "Finds a Person ",
            tags = {"People"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDto.class))
                ),
                @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PersonDto findById(@PathVariable(value = "id") long id) throws Exception {
        return service.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(
            consumes ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            produces ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML or YML representation of the person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public PersonDto create(@RequestBody PersonDto person) throws Exception {
        return service.create(person);
    }
//    @PostMapping(value = "/v2",
//            consumes ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
//            produces ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
//    public PersonDtoV2 createv2(@RequestBody PersonDtoV2 person) throws Exception {
//        return service.createV2(person);
//    }

    @PutMapping(
            consumes ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML},
            produces ={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Person",
            description = "Updates a Person by passing in a JSON, XML or YML representation of the person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public PersonDto update(@RequestBody PersonDto person) throws Exception {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person",
            description = "Deletes a Person by passing in a JSON, XML or YML representation of the person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
