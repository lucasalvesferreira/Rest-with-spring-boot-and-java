package com.api.Restwithspringbootandjava.data.dtos.v1;

import java.io.Serializable;

//@JsonPropertyOrder({"id","address","first_name","last_name","gender"})
public class PersonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
//    @JsonProperty("first_name")
    private String firstName;
//    @JsonProperty("last_name")
    private String lastName;
//    @JsonIgnore
    private String address;
    private String gender;


    public PersonDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
