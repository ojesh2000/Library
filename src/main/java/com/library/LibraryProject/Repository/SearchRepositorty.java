package com.library.LibraryProject.Repository;

import com.library.LibraryProject.Model.Book;

import java.util.List;

public interface SearchRepositorty {
    List<Book> findByText(String text);
    List<Book> findByTextGreater(String text , long num);
    List<Book> findByAuthId(Integer authId);
}
