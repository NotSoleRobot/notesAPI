package com.example.notesAPI.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
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
    public Iterable<Note> getAllNotes(){
        long count=noteRepository.count();
        if(count==0)
            return null;
        else{
            return noteRepository.findAll();
        }   

    }
    public Note getNoteById(int id){
        return noteRepository.findById(id).get();
    }
    public Note updateNoteById(int id,Note note){
       Optional<Note> optionalNote =  noteRepository.findById(id);
       if(optionalNote.isPresent()){
        Note existingNote=optionalNote.get();
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        return noteRepository.save(existingNote);
       }
       return new Note();
    }
    public Note deleteNoteById(int id){
        Optional<Note> optionalNote=noteRepository.findById(id);
        if(optionalNote.isPresent()){
            Note delNote=optionalNote.get();
            noteRepository.deleteById(id);
            return delNote;
        }
        return null;
        

    }

}