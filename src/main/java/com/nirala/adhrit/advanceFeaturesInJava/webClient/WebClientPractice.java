package com.meta.verse.advanceFeaturesInJava.webClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebClientPractice {

    @Autowired
    @Qualifier("rcsWebClient")
    private WebClient webClient;
    public static void main(String[] args) {
        SpringApplication.run(WebClientPractice.class, args);
        System.out.println("WebClientPractice");
        WebClientPractice webClientPractice = new WebClientPractice();
        Employee employee = new Employee();
        employee.setId(2);
        employee.setName("raman");
        employee.setStatus("passed");
        webClientPractice.postMessage(employee);
    }
    void postMessage(Employee empl){
        Mono<Employee> response = webClient.post()
                .uri("/employees")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(empl), Employee.class)
                .retrieve()
                .bodyToMono(Employee.class);
    }
}
