package com.example.notesAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.notesAPI.service.NoteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.notesAPI.model.Note;

@RestController

public class NoteController{
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService=noteService;
    }
    
    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note){
       return noteService.createNote(note);
    }

    @GetMapping("/notes")
    public Iterable<Note> getAllNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable int id){
        return noteService.getNoteById(id);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable int id,@RequestBody Note note){
        return noteService.updateNoteById(id,note);
    }

    @DeleteMapping("/notes/{id}")
    public Note deleteNote(@PathVariable int id){
        return noteService.deleteNoteById(id);
    }
}