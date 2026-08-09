package com.example.notesAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateNoteRequest{

    @NotBlank(message="Title cant be Blank")
    @Size(max=100,message="Cant exceed 100 characters")
    private String title;

    @NotBlank(message="Content cant be blank")
    @Size(max=1000,message="Content cant exceed 1000 characters")
    private String content;

    public void setTitle(String title){
        this.title=title;
    }
    public void setContent(String content){
        this.content=content;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }

}