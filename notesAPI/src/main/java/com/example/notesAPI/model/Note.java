package com.example.notesAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Note{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotBlank(message="Cant be blank")
    @Size(max=100,message="Cant be over 100 characters")
    private String title;

    @NotBlank(message="Cant be Blank")
    @Size(max=1000,message="Cant be over 1000 characters")
    private String content;

    public Note(){
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setContent(String content){
        this.content=content;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public void setId(int id){
        this.id=id;
    }
    
}