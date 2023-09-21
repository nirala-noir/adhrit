package com.meta.verse.mongoDbConnectionCRUD.repository;

import com.meta.verse.mongoDbConnectionCRUD.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepo
        extends MongoRepository<Book, Integer> {
    Page<Book> findAll(Pageable pageable);

    @Query(value="{id:?0}")             // SQL Equivalent : SELECT * FROM BOOK WHERE ID=?
    Optional<Book> getBookByIdTemp(Integer id);

    //select 5 rows from table
    @Query(value = "{bookName: ?0}")
    List<Book> findByBookName(String book);


//    //TODO: create a method to acheive the below line
    @Query(value = "{id:{$ne:null}}",count = true)
    Integer getAllEntries();

    @Query(value = "{id:?0 }, {bookName:?1}") // sql: select * from book where id=? or bookName=?;
    List<Book> getBooksByAuthorOrName(Integer id, String name);
    @Query(value = "{id:?0 ,bookName:?1}")  // sql: select * from book where id=? and bookname=?;
    List<Book> getBooksByAuthorOrName2(Integer id, String name);

    @Query(value ="{id:?0}")
    List<Book> getEntriesOfcount(Integer n);

    @Query(value = "SELECT u FROM User u")
    List<Book> getLast10Values(Sort sort);
//    @Query
//    void deleteAllEntries();
    //first indexed on the base of something then extract all upto n
    //then










//    @Query("{pages : {$lt: ?0}}")                                 // SQL Equivalent : SELECT * FROM BOOK where pages<?
//        //@Query("{ pages : { $gte: ?0 } }")                        // SQL Equivalent : SELECT * FROM BOOK where pages>=?
//        //@Query("{ pages : ?0 }")                                      // SQL Equivalent : SELECT * FROM BOOK where pages=?
//    List<Book> getBooksByPages(Integer pages);
//
//
//    @Query("{author : ?0}")                                         // SQL Equivalent : SELECT * FROM BOOK where author = ?
//    List<Book> getBooksByAuthor(String author);
//
//
//    @Query("{author: ?0, cost: ?1}")                            // SQL Equivalent : SELECT * FROM BOOK where author = ? and cost=?
//        //@Query("{$and :[{author: ?0},{cost: ?1}] }")
//    List<Book> getBooksByAuthorAndCost(String author, Double cost);
//
//
//    @Query("{$or :[{author: ?0},{name: ?1}]}")            //SQL Equivalent : select count(*) from book where author=? or name=?
//    List<Book> getBooksByAuthorOrName(String author, String name);
//
//
//    @Query(value ="{author: ?0}", count=true)           //SQL Equivalent : select count(*) from book where author=?
//    Integer getBooksCountByAuthor(String author);
//
//    //Sorting
//    @Query(value = "{author:?0}", sort= "{name:1}") //ASC
//    //@Query(value = "{author=?0}", sort= "{name:-1}") //DESC
//    List<Book> getBooksByAuthorSortByName(String author);
//
//    //------------------- @Query with Projection ---------------------------------------
//    @Query(value= "{pages: ?0}", fields="{name:1, author:1}")   // only data of name & author properties will be displayed
//    //@Query(value= "{pages: ?0}", fields="{name:1, author:1, cost:1, pages:1}") // will display all properties data
//    List<Book> getBookNameAndAuthorByPages(Integer pages);
//
//
//    @Query(value= "{author : ?0}")           // SQL Equivalent : SELECT * FROM BOOK select * from books where author=?
//    List<Book> getAllBooksByAuthor(String author);
//
//    //------------------MongoDB Regular Expressions--------------------------------------
//    @Query("{ author : { $regex : ?0 } }")
//    List<Book> getBooksByAuthorRegEx(String author);

}

