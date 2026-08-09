package  com.example.notesAPI.dto;

public class UpdateNoteRequest{
    private String title;
    private String content;
    public void setTitle(String title){
        this.title=title;
    }
    public void setContent(String content){
        this.content=content;
    }
    public String getContent(){
        return content;
    }
    public String getTitle(){
        return title;
    }
}