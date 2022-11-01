package com.api.Restwithspringbootandjava.service;

import com.api.Restwithspringbootandjava.contrllers.BookController;
import com.api.Restwithspringbootandjava.data.dtos.v1.BookDto;
import com.api.Restwithspringbootandjava.exceptions.RequiredObjectIsNullException;
import com.api.Restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.api.Restwithspringbootandjava.mapper.DozerMapper;
import com.api.Restwithspringbootandjava.model.BookModel;
import com.api.Restwithspringbootandjava.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    private Logger logger = Logger.getLogger(BookService.class.getName());


    public List<BookDto> findAll() {

        logger.info("Finding all people!");
        var book= DozerMapper.parseListObjects(repository.findAll(),BookDto.class);
        book
             .stream()
             .forEach(p -> {
                 try {
                     p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
             });
        return book;
    }

    public BookDto findById(Long id) throws Exception {

        logger.info("Finding one book!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));
        var dto = DozerMapper.parseObject(entity,BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return dto;
    }

    public BookDto create(BookDto book) throws Exception {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Create one book!");
        var entity = DozerMapper.parseObject(book, BookModel.class);
        var dto= DozerMapper.parseObject(repository.save(entity),BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;

    }

//    public BookDtoV2 createV2(BookDtoV2 book){
//
//        logger.info("Create one book with v2!");
//        var entity = mapper.convertDtoToEntity(book);
//
//        var dto=mapper.convertEntityToDto(repository.save(entity));
//        return dto;
//    }
    public  BookDto update(BookDto book) throws Exception {

        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("updating one book!");
        var entity = repository.findById(book.getKey()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto= DozerMapper.parseObject(repository.save(entity),BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;

    }
    public  void delete(Long id){

        logger.info("Deleting one book!");
        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id!"));

        repository.delete(entity);
    }


}
