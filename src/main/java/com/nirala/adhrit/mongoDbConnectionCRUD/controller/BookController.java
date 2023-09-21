package com.meta.verse.mongoDbConnectionCRUD.controller;

import com.meta.verse.mongoDbConnectionCRUD.model.Book;
import com.meta.verse.mongoDbConnectionCRUD.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepo repo;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        if(!repo.existsById(book.getId())) {
            repo.save(book);
            return "Added Successfully";
        }

//        Book temp = book;
//        for(int i=1;i<14;i++){
//            temp.setId(temp.getId()+1);
//            temp.setBookName(temp.getBookName()+i);
//            temp.setAuthorName(temp.getAuthorName()+i);
//            repo.save(temp);
//        }
        return  "already exist";

    }

    @GetMapping("/findAllBooks")
    public List<Book> getBooks() {

        List<Book> list= repo.findAll();

        return list;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        repo.deleteById(id);
        return "Deleted Successfully";
    }
    @PutMapping("/updateValue")
    public String updateExisting(@RequestBody Book book){

        if(repo.existsById(book.getId())){
            Optional temp=repo.findById(book.getId());
            Book book1 = (Book) temp.get();
            book1.setBookName(book.getBookName());
            repo.save(book1);
            return "only bookNameChanged";
        }
        else{
            repo.save(book);
        }
        return "update successfull";
    }
}
