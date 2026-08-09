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

import com.example.notesAPI.dto.CreateNoteRequest;
import com.example.notesAPI.model.Note;
import com.example.notesAPI.service.NoteService;

import jakarta.validation.Valid;

@RestController

public class NoteController{
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService=noteService;
    }
    
    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@Valid @RequestBody CreateNoteRequest request){
        Note note=new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
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
         return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(note);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable int id,@Valid @RequestBody Note note){
        Note updatedNote= noteService.updateNoteById(id,note);
            return ResponseEntity
                                .ok(updatedNote);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable int id){
        Note deletedNote= noteService.deleteNoteById(id);
            return ResponseEntity
                                .ok(deletedNote);
    }
}