package com.meta.verse.mongoDbConnectionCRUD;

import com.meta.verse.mongoDbConnectionCRUD.model.Book;
//import com.meta.verse.mongoDbConnectionCRUD.query.QueryOperationPerform;
import com.meta.verse.mongoDbConnectionCRUD.model.Customer;
import com.meta.verse.mongoDbConnectionCRUD.repository.BookRepo;

import com.meta.verse.mongoDbConnectionCRUD.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.swing.text.Document;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class MongoMain {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private CustomerRepo customerRepo;

//    @Autowired
//    JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(MongoMain.class,args);
        System.out.println("started mongo classes");

        //MongoCollection<Book> mongoCollection = databases.get
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        List<Book> alpha = bookRepo.findByBookName("beta");
//        if (alpha.size()>0){
//            System.out.printf("raman kumar nirala");
//        }

//         Page<Book> page = bookRepo.findAll(PageRequest.of(0, 10));
//         page.forEach(System.out::println);

//        List<Customer> cust = customerRepo.findAll();
//        System.out.println(cust);

        //book query function call in then function which save all data in another boo_backup
        //book query deletion function call

//        Optional<Book> opt = bookRepo.getBookByIdTemp(100);
//        Book_BackUp book_backUp = new Book_BackUp(opt.get().getId(),opt.get().getBookName(),opt.get().getAuthorName());
//        bookBackUp.save(book_backUp);
//        bookRepo.deleteAllEntries();//complete all above

//        Optional<Book> opt = bookRepo.getBookByIdTemp(103);
//        Customer customer = new Customer(opt.get().getId(),opt.get().getBookName(),opt.get().getAuthorName());
//        customerRepo.save(customer);
//        bookRepo.delete(opt.get());

//        List<Book> val = bookRepo.getLast10Values(Sort.by("_id"));
//        val.forEach(System.out::println);
//        if(opt.isPresent()) {
//            System.out.println(opt.get());
//        }
//        else {
//            System.out.println("DATA NOT FOUND");
//        }
//        Page<Book> page = bookRepo.findAll(PageRequest.of(0,10, Sort.by(Sort.Order.asc("_id"))));
//        System.out.println(page.getContent());
//        System.out.println(bookRepo.getAllEntries());
//        List<Book> list =bookRepo.getBooksByAuthorOrName(102,"java11");
//        if(!list.isEmpty())
//        for(Book book:list){
//            System.out.println(book.getId() + " "+book.getBookName()+" "+book.getAuthorName());
//        }
//        else{
//            System.out.println("list is empty");
//        }
        //queryOperationPerform.getBookByIdTemp(100);
//        queryOperationPerform.fetchAllData();
//        queryOperationPerform.performAggregate(); //fetched aggregate data here
//    }
}








//reference link: https://www.geeksforgeeks.org/spring-boot-crud-operations-using-mongodb/

/*step one get uriString from mongodb compass
 * put uriString in app.prop
 * run main with @enablemongoRepo annotation
 * you will find connection established
 * create a repo interface where we can extend mongoRepository which will take a class datatype with feilds
 * then create end points and use repo interface to save and delete function which is inherited from MongoRepository class
 *
 * */