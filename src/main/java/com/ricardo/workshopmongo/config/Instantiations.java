package com.ricardo.workshopmongo.config;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ricardo.workshopmongo.domain.Post;
import com.ricardo.workshopmongo.domain.User;
import com.ricardo.workshopmongo.dto.AuthorDTO;
import com.ricardo.workshopmongo.dto.CommentDTO;
import com.ricardo.workshopmongo.repository.PostRepository;
import com.ricardo.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiations implements CommandLineRunner {

    DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, LocalDate.parse("21/03/2018", fmt), "Partiu viagem", "Vou viajar, abraços!!!", new AuthorDTO(maria) );
        Post post2 = new Post(null, LocalDate.parse("25/03/2018", fmt), "Bom dia!!", "Acordei Feliz hoje!", new AuthorDTO(maria) );

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", LocalDate.parse("21/03/2018", fmt), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", LocalDate.parse("21/03/2018", fmt), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um otimo dia", LocalDate.parse("21/03/2018", fmt), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
    
}
