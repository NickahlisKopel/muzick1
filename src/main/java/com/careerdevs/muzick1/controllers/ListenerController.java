package com.careerdevs.muzick1.controllers;

import com.careerdevs.muzick1.models.Listener;
import com.careerdevs.muzick1.repositories.ListenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/listeners")
public class ListenerController {

    @Autowired
    private ListenerRepository listenerRepository;


    @GetMapping("/test")
    public ResponseEntity<?> testRoute(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Listener> createListener(@RequestBody Listener newListener){
        Listener listener = listenerRepository.save(newListener);

        return new ResponseEntity<>(listener, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Listener>> getAllListeners(){
        List<Listener> listeners = listenerRepository.findAll();
        return new ResponseEntity<>(listeners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneListener(@PathVariable Long id){
        Optional<Listener> listener = listenerRepository.findById(id);

        if(listener.isEmpty()){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(listener, HttpStatus.OK);

    }
}
