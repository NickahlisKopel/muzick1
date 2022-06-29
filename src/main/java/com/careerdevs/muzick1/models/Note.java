package com.careerdevs.muzick1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "listener_id",referencedColumnName = "id")
   // @JsonIgnoreProperties({"age"}) //ignore these
    //@JsonIncludeProperties({"id"})//only include these
    private Listener listener;

    public Note() {
    }

    public Note(Long id, String title, String body, Listener listener) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.listener = listener;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
