package com.meta.verse.fetchDataFromPropertyFile;

import com.meta.verse.VerseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
//completed
@SpringBootApplication
@PropertySource("classpath:product.properties")
public class ProductMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(VerseApplication.class, args);
        Product product= applicationContext.getBean("product", Product.class);
        System.out.println(product);
    }

}
