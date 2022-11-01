package com.api.Restwithspringbootandjava.data.dtos.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id","author","launchDate","price","title"})
public class BookDto extends RepresentationModel<BookDto> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String author;

    private Date launchDate;

    private Double price ;

    private String title;


    public BookDto() {

    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto bookDto)) return false;
        if (!super.equals(o)) return false;
        return getKey().equals(bookDto.getKey()) && getAuthor().equals(bookDto.getAuthor()) && getLaunchDate().equals(bookDto.getLaunchDate()) && getPrice().equals(bookDto.getPrice()) && getTitle().equals(bookDto.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getAuthor(), getLaunchDate(), getPrice(), getTitle());
    }
}

