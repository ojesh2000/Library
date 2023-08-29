package com.library.LibraryProject.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Books")
public class Book {
    private String id ;
    private int CopiesAvailable ;
    private int authorId ;
    private String genre ;

    public Book(){

    }

    public String getId (){
        return id ;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getCopiesAvailable(){
        return CopiesAvailable ;
    }

    public void setCopiesAvailable(int CopiesAvailable) {
        this.CopiesAvailable=CopiesAvailable ;
    }
    public int getAuthorId(){
        return authorId ;
    }

    public void setAuthorId(int authorId) {
        this.authorId=authorId ;
    }

    public String getGenre (){
        return genre ;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "{Id='" + id + '\'' +
                "CopiesAvailable='" + CopiesAvailable + '\'' +
                ", authorId='" + authorId + '\'' +
                ", genre=" + genre +
                '}';
    }
}
