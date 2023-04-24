package com.ricardo.workshopmongo.services;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.workshopmongo.domain.Post;

import com.ricardo.workshopmongo.repository.PostRepository;

import com.ricardo.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));

    }

    public List<Post> findByTitle(String text){
        return repository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate){        
        
        return repository.fullSeach(text, minDate, maxDate);
    }



}