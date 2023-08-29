package com.library.LibraryProject.Repository;

import com.library.LibraryProject.Model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
