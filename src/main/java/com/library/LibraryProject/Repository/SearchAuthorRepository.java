package com.library.LibraryProject.Repository;

import com.library.LibraryProject.Model.Author;

import java.util.List;

public interface SearchAuthorRepository {
    List<Integer> findAuthors(String authName);

    List<Author> findAuthorsByRegex(String likeAuth);
}
