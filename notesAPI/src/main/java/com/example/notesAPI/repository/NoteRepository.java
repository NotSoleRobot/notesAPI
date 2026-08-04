package com.example.notesAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.notesAPI.model.Note;


public interface NoteRepository extends JpaRepository<Note,Integer>{
            
}