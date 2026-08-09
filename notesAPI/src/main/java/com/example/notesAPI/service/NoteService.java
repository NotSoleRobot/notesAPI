package com.example.notesAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.notesAPI.exception.NoteNotFoundException;
import com.example.notesAPI.model.Note;
import com.example.notesAPI.repository.NoteRepository;

@Service
public class NoteService{
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository=noteRepository;
    }
    public Note createNote(Note note){
       return noteRepository.save(note);
    }
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }
    public Note getNoteById(int id){
        Optional<Note> optionalNote=noteRepository.findById(id);
        if(!optionalNote.isPresent()){
            throw new NoteNotFoundException("Note with id:"+id+"was not found");
        }
        return optionalNote.get();
    }
    public Note updateNoteById(int id,Note note){
       Optional<Note> optionalNote =  noteRepository.findById(id);
       if(!optionalNote.isPresent()){
            throw new NoteNotFoundException("Note with id:"+id+"was not found");
       }
         Note existingNote=optionalNote.get();
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        return noteRepository.save(existingNote);
    }
    public Note deleteNoteById(int id){
        Optional<Note> optionalNote=noteRepository.findById(id);
        if(!optionalNote.isPresent()){
            throw new NoteNotFoundException("Note with id:"+id+"was not found");
        }
         Note delNote=optionalNote.get();
            noteRepository.deleteById(id);
            return delNote;
    }

}