package com.library.LibraryProject.Repository;

import com.library.LibraryProject.Model.Book;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepositorty{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;


    @Override
    public List<Book> findByText(String text) {
        final List<Book> books = new ArrayList<>();
        MongoDatabase database = client.getDatabase("BooksDb");
        MongoCollection<Document> collection = database.getCollection("Books");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default")
                                .append("text",
                                        new Document("query", text)
                                                .append("path", "genre"))),
                new Document("$sort",
                        new Document("authorId", 1L)),
                new Document("$limit", 5L)));

        result.forEach(doc -> books.add(converter.read(Book.class,doc)));

        return books;
    }

    public List<Book> findByTextGreater(String text , long num){
        final List<Book> books = new ArrayList<>();
        MongoDatabase database = client.getDatabase("BooksDb");
        MongoCollection<Document> collection = database.getCollection("Books");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default")
                                .append("text",
                                        new Document("query", text)
                                                .append("path", "genre"))),
                new Document("$match",
                        new Document("CopiesAvailable",
                                new Document("$gte", num))),
                new Document("$limit", 5L)));

        result.forEach(doc -> books.add(converter.read(Book.class,doc)));

        return books;
    }

    public List<Book> findByAuthId(Integer authId){
        final List<Book> books = new ArrayList<>();
        MongoDatabase database = client.getDatabase("BooksDb");
        MongoCollection<Document> collection = database.getCollection("Books");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "default")
                        .append("equals",
                                new Document("value", (long)(authId))
                                        .append("path", "authorId")))));

        result.forEach(doc -> books.add(converter.read(Book.class,doc)));

        return books;
    }
}
