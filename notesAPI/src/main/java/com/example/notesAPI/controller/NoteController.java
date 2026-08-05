package com.example.notesAPI.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.notesAPI.model.Note;
import com.example.notesAPI.service.NoteService;

@RestController

public class NoteController{
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService=noteService;
    }
    
    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@RequestBody Note note){
       Note savedNote= noteService.createNote(note);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(savedNote);
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(){

        List<Note> notes= noteService.getAllNotes();
            return ResponseEntity.ok(notes);
        
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable int id){
         Note note= noteService.getNoteById(id);
         if(note==null){
            return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .build();
         }
         return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(note);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable int id,@RequestBody Note note){
        
        Note updatedNote= noteService.updateNoteById(id,note);
        if(updatedNote!=null){
            return ResponseEntity
                                .ok(updatedNote);
        }
        return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .build();
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable int id){
        Note deletedNote= noteService.deleteNoteById(id);
        if(deletedNote!=null){
            return ResponseEntity
                                .ok(deletedNote);
        }
        return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .build();
    }
}