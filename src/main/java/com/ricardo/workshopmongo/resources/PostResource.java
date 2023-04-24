package com.ricardo.workshopmongo.resources;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ricardo.workshopmongo.domain.Post;
import com.ricardo.workshopmongo.resources.util.URL;
import com.ricardo.workshopmongo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
    

    @Autowired
    private PostService service;

  
    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findByID(@PathVariable String id){
        Post obj = service.findById(id);        
        
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        
        List<Post> list = service.findByTitle(text);
        
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
        @RequestParam(value="text", defaultValue = "") String text,
        @RequestParam(value="minDate", defaultValue = "") String minDate,
        @RequestParam(value="maxDate", defaultValue = "") String maxDate)
        {
        text = URL.decodeParam(text);
        LocalDate min = URL.convertDate(minDate, LocalDate.ofEpochDay(100));
        
        LocalDate max = URL.convertDate(maxDate, LocalDate.now());

        List<Post> list = service.fullSearch(text, min, max);
        
        return ResponseEntity.ok().body(list);
    }


}