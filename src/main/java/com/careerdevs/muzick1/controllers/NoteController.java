package com.careerdevs.muzick1.controllers;

import com.careerdevs.muzick1.models.Listener;
import com.careerdevs.muzick1.models.Note;
import com.careerdevs.muzick1.repositories.ListenerRepository;
import com.careerdevs.muzick1.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ListenerRepository listenerRepository;

    @GetMapping("/test")
    public ResponseEntity<?> testRoute(){
        return new ResponseEntity<>("note route", HttpStatus.OK);
    }

    @PostMapping("/{listenerId}")
    public ResponseEntity<?> createNote(@PathVariable Long listenerId, @RequestBody Note newNote) {
        // TODO get listener id from authentication token
        Listener listener = listenerRepository.findById(listenerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        newNote.setListener(listener);

        Note note = noteRepository.save(newNote);
        return new ResponseEntity<>(note, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllNotes(){
        List<Note> notes = noteRepository.findAll();
        return new ResponseEntity<>(notes,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneNote(@PathVariable Long id){
       Optional<Note> note = noteRepository.findById(id);
       if(note.isEmpty()){
           return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping("/listener/{listenerId}")
    public ResponseEntity<?> getNotesByListener(@PathVariable Long listenerId){
     List<Note> notes = noteRepository.findAllByListener_id(listenerId);

     return new ResponseEntity<>(notes,HttpStatus.OK);
    }

}
