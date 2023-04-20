package com.ricardo.workshopmongo.domain;

import java.io.Serializable;
import java.time.LocalDate;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ricardo.workshopmongo.dto.AuthorDTO;
import com.ricardo.workshopmongo.dto.UserDTO;

@Document
public class Post implements Serializable {

    private static final long serialVersionUID  = 1L;

    @Id
    private String Id;
    private LocalDate date;
    private String title;
    private String body;
    private AuthorDTO author;

    public Post  (){

    }

    public Post(String id, LocalDate date, String title, String body, AuthorDTO author) {
        Id = id;
        this.date = date;
        this.title = title;
        this.body = body;
        this.author = author;
        
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Id == null) ? 0 : Id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (Id == null) {
            if (other.Id != null)
                return false;
        } else if (!Id.equals(other.Id))
            return false;
        return true;
    }

    

    


}
