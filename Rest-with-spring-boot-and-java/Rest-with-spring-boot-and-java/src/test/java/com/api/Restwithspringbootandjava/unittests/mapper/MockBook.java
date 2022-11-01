package com.api.Restwithspringbootandjava.unittests.mapper;

import com.api.Restwithspringbootandjava.data.dtos.v1.BookDto;
import com.api.Restwithspringbootandjava.model.BookModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public BookModel mockEntity() {
        return mockEntity(0);
    }

    public BookDto mockVO() {
        return mockVO(0);
    }

    public List<BookModel> mockEntityList() {
        List<BookModel> books = new ArrayList<BookModel>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDto> mockVOList() {
        List<BookDto> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public BookModel mockEntity(Integer number) {
        BookModel book = new BookModel();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

    public BookDto mockVO(Integer number) {
        BookDto book = new BookDto();
        book.setKey(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }


}
