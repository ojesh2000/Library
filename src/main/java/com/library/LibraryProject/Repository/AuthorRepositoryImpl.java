package com.library.LibraryProject.Repository;

import com.library.LibraryProject.Model.Author;
import com.library.LibraryProject.Model.Book;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class AuthorRepositoryImpl implements SearchAuthorRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Integer> findAuthors(String authName) {
        List<Integer> ids = new ArrayList<>();
        List<Author> auths = new ArrayList<>();
        MongoDatabase database = client.getDatabase("BooksDb");
        MongoCollection<Document> collection = database.getCollection("Authors");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "default")
                        .append("text",
                                new Document("query", authName)
                                        .append("path", "name")))));

        result.forEach(doc -> auths.add(converter.read(Author.class,doc)));

        for(Author a : auths){
            System.out.println(a.getId());
        }
        auths.forEach(auth -> ids.add(Integer.parseInt(auth.getId())));

        return ids;
    }

    public List<Author> findAuthorsByRegex(String likeAuth){
        List<Author> auths = new ArrayList<>();
        MongoDatabase database = client.getDatabase("BooksDb");
        MongoCollection<Document> collection = database.getCollection("Authors");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "default")
                        .append("regex",
                                new Document("query", "(.*)" + likeAuth + "(.*)")
                                        .append("path", "name")
                                        .append("allowAnalyzedField", true)))));

        result.forEach(doc -> auths.add(converter.read(Author.class,doc)));

        return auths;
    }
}
