package com.library.LibraryProject.Service;

import com.library.LibraryProject.Model.Author;
import com.library.LibraryProject.Model.Book;
import com.library.LibraryProject.Repository.AuthorRepository;
import com.library.LibraryProject.Repository.BookRepository;
import com.library.LibraryProject.Repository.SearchAuthorRepository;
import com.library.LibraryProject.Repository.SearchRepositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository arepo;

    @Autowired
    SearchAuthorRepository sarepo;

    @Autowired
    BookRepository repo;

    @Autowired
    SearchRepositorty srepo;
    public String getRoot(){
        return "Hello World";
    }

    public List<Book> getAllBooks(){
        return repo.findAll();
    }

    public List<Author> getAllAuthors(){return arepo.findAll();}

    public List<Book> search(String text){
        return srepo.findByText(text);
    }

    public List<Book> searchByTextAndNum(String text , long num){
        return srepo.findByTextGreater(text , num);
    }

    public void saveBook(Book book){
        if(book.getId() == null || book.getGenre() == null)return ;
        repo.save(book);
    }

    public void saveAuthor(Author auth)    {
        System.out.println(auth.getAddress());
        arepo.insert(auth);
    }

    public List<Book> findBooksBasedOnAuth(String authName){
        List<Integer> authIds = sarepo.findAuthors(authName);
        List<Book> booksByAuth = new ArrayList<>();
        for(Integer id : authIds){
            List<Book> reqd = srepo.findByAuthId(id);
            booksByAuth.addAll(reqd);
        }
        return booksByAuth;
    }

    public List<Author> findAuthorsRegex(String likeAuth){
        return sarepo.findAuthorsByRegex(likeAuth);
    }

}
