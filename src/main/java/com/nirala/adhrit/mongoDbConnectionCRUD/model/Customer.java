package com.meta.verse.mongoDbConnectionCRUD.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "book2")
public class Customer {
    @Id
    private int id;
    private String bookName;
    private String authorName;

    public Customer(int id, String bookName, String authorName) {
        super();
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
    }
}
