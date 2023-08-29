package com.library.LibraryProject.Repository;

import com.library.LibraryProject.Model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
