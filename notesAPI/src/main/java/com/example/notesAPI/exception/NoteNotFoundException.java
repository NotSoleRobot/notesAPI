package com.example.notesAPI.exception;

public class NoteNotFoundException extends RuntimeException{
    public  NoteNotFoundException(String message){
        super(message);
    }
}