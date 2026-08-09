package com.example.notesAPI.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

        @ExceptionHandler(NoteNotFoundException.class)
        public ResponseEntity<String> handleNoteNotFound(NoteNotFoundException e){
            return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(e.getMessage());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException e){
                Map<String,String> errors=new HashMap();

                e.getBindingResult()
                        .getFieldErrors()
                        .forEach(
                                error->errors.put(
                                        error.getField(),error.getDefaultMessage()
                                )
                        );
                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(errors);
        }
}