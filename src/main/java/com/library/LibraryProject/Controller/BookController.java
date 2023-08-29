package com.library.LibraryProject.Controller;

import com.library.LibraryProject.Model.Author;
import com.library.LibraryProject.Model.Book;
import com.library.LibraryProject.Repository.AuthorRepository;
import com.library.LibraryProject.Repository.BookRepository;
import com.library.LibraryProject.Repository.SearchAuthorRepository;
import com.library.LibraryProject.Repository.SearchRepositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    AuthorRepository arepo;

    @Autowired
    SearchAuthorRepository sarepo;

    @Autowired
    BookRepository repo;

    @Autowired
    SearchRepositorty srepo;
    @RequestMapping("/")
    public String getRoot(){
        return "Hello World";
    }

    @GetMapping("/allbooks")
    public List<Book> getAllBooks(){
        return repo.findAll();
    }

    @GetMapping("allauthors")
    public List<Author> getAllAuthors(){return arepo.findAll();}

    @GetMapping("/books/{text}")
    public List<Book> search(@PathVariable String text){
        return srepo.findByText(text);
    }

    @GetMapping("/books/{text}/{num}")
    public List<Book> searchByTextAndNum(@PathVariable String text , @PathVariable long num){
        return srepo.findByTextGreater(text , num);
    }

    @PostMapping("/save-book")
    public void saveBook(@RequestBody Book book){
        if(book.getId() == null || book.getGenre() == null)return ;
        repo.save(book);
    }

    @PostMapping("/save-author")
    public void saveAuthor(@RequestBody Author auth)    {
        System.out.println(auth.getAddress());
        arepo.insert(auth);
    }

    @GetMapping("/find-books/{authName}")
    public List<Book> findBooksBasedOnAuth(@PathVariable String authName){
        List<Integer> authIds = sarepo.findAuthors(authName);
        List<Book> booksByAuth = new ArrayList<>();
        for(Integer id : authIds){
            List<Book> reqd = srepo.findByAuthId(id);
            booksByAuth.addAll(reqd);
        }
        return booksByAuth;
    }

    @GetMapping("/find-auth/{likeAuth}")
    public List<Author> findAuthorsRegex(@PathVariable String likeAuth){
        return sarepo.findAuthorsByRegex(likeAuth);
    }

}
