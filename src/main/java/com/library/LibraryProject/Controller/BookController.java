package com.library.LibraryProject.Controller;

import com.library.LibraryProject.Model.Author;
import com.library.LibraryProject.Model.Book;
import com.library.LibraryProject.Repository.AuthorRepository;
import com.library.LibraryProject.Repository.BookRepository;
import com.library.LibraryProject.Repository.SearchAuthorRepository;
import com.library.LibraryProject.Repository.SearchRepositorty;
import com.library.LibraryProject.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    AuthorRepository arepo;

    @Autowired
    private BookService service;
    @RequestMapping("/")
    public String getRoot(){
        return "Hello World";
    }

    @GetMapping("/allbooks")
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }

    @GetMapping("allauthors")
    public List<Author> getAllAuthors(){return service.getAllAuthors();}

    @GetMapping("/books/{text}")
    public List<Book> search(@PathVariable String text){
        return service.search(text);
    }

    @GetMapping("/books/{text}/{num}")
    public List<Book> searchByTextAndNum(@PathVariable String text , @PathVariable long num){
        return service.searchByTextAndNum(text , num);
    }

    @PostMapping("/save-book")
    public void saveBook(@RequestBody Book book){
        service.saveBook(book);
    }

    @PostMapping("/save-author")
    public void saveAuthor(@RequestBody Author auth)    {
        service.saveAuthor(auth);
    }

    @GetMapping("/find-books/{authName}")
    public List<Book> findBooksBasedOnAuth(@PathVariable String authName){
        return service.findBooksBasedOnAuth(authName);
    }

    @GetMapping("/find-auth/{likeAuth}")
    public List<Author> findAuthorsRegex(@PathVariable String likeAuth){
        return service.findAuthorsRegex(likeAuth);
    }

}
