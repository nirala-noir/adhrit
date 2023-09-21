package com.meta.verse.debugWhale;

import com.meta.verse.debugWhale.controller.ApiCollection;
import com.meta.verse.debugWhale.dto.OptinResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReceiveData {
    public static void main(String[] args) {
        SpringApplication.run(ReceiveData.class,args);
        System.out.println("calling api and receive data via endpoint");
        //ApiCollection apiCollection = new ApiCollection();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        OptinResponseDto optinResponseDto= apiCollection.getAllValue(new OptinResponseDto(true, "Hi", "Hello"));
//        System.out.println(optinResponseDto.getOptinDate()+"alpha");
        //apiCollection.templateCheck();
        CallingClass callingClass = new CallingClass();
        System.out.println(callingClass.funcTemp()+"function called");
    }

}
