package com.api.Restwithspringbootandjava.integretionstests.controller.withjson;


import com.api.Restwithspringbootandjava.configs.TestConfigs;
import com.api.Restwithspringbootandjava.integretionstests.dto.AccountCredentialsDto;
import com.api.Restwithspringbootandjava.integretionstests.dto.PersonDto;
import com.api.Restwithspringbootandjava.integretionstests.dto.TokenDto;
import com.api.Restwithspringbootandjava.integretionstests.testcontainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification ;
    private static ObjectMapper objectMapper;

    private static PersonDto person;

    @BeforeAll
    public static void setup(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonDto();
    }

    @Test
    @Order(0)
    public void authorization() throws IOException {
        AccountCredentialsDto user = new AccountCredentialsDto("leandro", "admin123");

        var accessToken = given()
                .basePath("/auth/signin")
                    .port(TestConfigs.SERVER_PORT)
                    .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                    .when()
                .post()
                    .then()
                        .statusCode(200)
                            .extract()
                            .body()
                                .as(TokenDto.class)
                            .getAccessToken();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

    }
    @Test
    @Order(1)
    public void testCreate() throws IOException {
        mockPerson();

        var content =
            given().spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_JSON)
               .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_REST_API)
               .body(person)
                    .when()
                    .post()
               .then()
                    .statusCode(200)
               .extract()
                    .body()
                         .asString();

        PersonDto persistedPerson = objectMapper.readValue(content,PersonDto.class);
        person = persistedPerson;

        assertNotNull(persistedPerson);
        assertNotNull(persistedPerson.getId());
        assertNotNull(persistedPerson.getFirstName());
        assertNotNull(persistedPerson.getLastName());
        assertNotNull(persistedPerson.getAddress());
        assertNotNull(persistedPerson.getGender());

        assertTrue(persistedPerson.getId() > 0);

        assertEquals("Lucas", persistedPerson.getFirstName());
        assertEquals("Alves", persistedPerson.getLastName());
        assertEquals("Brazuca", persistedPerson.getAddress());
        assertEquals("Male", persistedPerson.getGender());

    }


    @Test
    @Order(2)
    public void testCreateWithWrongOrigin() throws IOException {
        mockPerson();

        var content =
                given().spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_REST_NO_API)
               .body(person)
            .when()
                 .post()
            .then()
                .statusCode(403)
                       .extract()
                            .body()
                                .asString();


        assertNotNull(content);
        assertEquals("Invalid CORS request", content);

    }
    @Test
    @Order(3)
    public void findById() throws IOException {
        mockPerson();

        var content =
                given().spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_REST_API)
                        .pathParams("id",person.getId())
                        .when()
                        .get("{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asString();

        PersonDto persistedPerson = objectMapper.readValue(content,PersonDto.class);
        person = persistedPerson;

        assertNotNull(persistedPerson);
        assertNotNull(persistedPerson.getId());
        assertNotNull(persistedPerson.getFirstName());
        assertNotNull(persistedPerson.getLastName());
        assertNotNull(persistedPerson.getAddress());
        assertNotNull(persistedPerson.getGender());

        assertTrue(persistedPerson.getId() > 0);

        assertEquals("Lucas", persistedPerson.getFirstName());
        assertEquals("Alves", persistedPerson.getLastName());
        assertEquals("Brazuca", persistedPerson.getAddress());
        assertEquals("Male", persistedPerson.getGender());

    }
    @Test
    @Order(4)
    public void findByIdWithWhongOrigin() throws IOException {
        mockPerson();

        var content =
                given().spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_REST_NO_API)
                        .pathParams("id",person.getId())
                        .when()
                        .get("{id}")
                        .then()
                        .statusCode(403)
                        .extract()
                        .body()
                        .asString();



        assertNotNull(content);
        assertEquals("Invalid CORS request", content);

    }


    private void mockPerson() {
        person.setFirstName("Lucas");
        person.setLastName("Alves");
        person.setAddress("Brazuca");
        person.setGender("Male");
    }

}
