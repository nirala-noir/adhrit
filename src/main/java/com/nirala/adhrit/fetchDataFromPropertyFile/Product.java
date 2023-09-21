package com.meta.verse.fetchDataFromPropertyFile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Product {
    @Value("${product.title}")
    private String title;

    @Value("${product.version}")
    private Double version;

    @Override
    public String toString() {
        return "Product [title=" + title + ", version=" + version + "]";
    }

}
