package com.meta.verse.debugWhale.controller;

import com.google.gson.JsonObject;
import com.meta.verse.debugWhale.dto.OptinResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiCollection {
    @PostMapping("/apiData")
    public OptinResponseDto getAllValue(@RequestBody OptinResponseDto optinResponseDto){
        return optinResponseDto;
    }

//    @Autowired
//    private RestTemplate restTemplate;
//    public <T, V> V publishRequest(String url, T t, HttpMethod httpMethod, Class className) {
//        //PullPlatformReceiverCommonLogger.INSTANCE.getLogger().info("RestCallService::publishRequest::send request to url: {}, request payload: {}",url,t);
//        V responseData = null;
//        try {
//            ResponseEntity<V> responseEntity = restTemplate.exchange(url, httpMethod, new HttpEntity<>(t, getHeaders()), className);
//            if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                responseData = responseEntity.getBody();
//            }else {
//                //PullPlatformReceiverCommonLogger.INSTANCE.getLogger().info("RestCallService::sendRequest::send request to url: {}::::response entity::::{}::::",url,responseEntity);
//            }
//        } catch (Exception e) {
//            //PullPlatformReceiverErrorLogger.INSTANCE.getLogger().error("RestCallService::publishRequest::EXCEPTION IN PUBLISH REQUEST :: URL:: {}, AND REQUEST:: {}",url,t, e);
//        }
//        return responseData;
//    }
//    private HttpHeaders getHeaders(){
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        return requestHeaders;
//    }
//
//    public void templateCheck(){
//        OptinResponseDto optinResponseDto = publishRequest("http://localhost:8080/welcome", new JsonObject(), HttpMethod.GET, String.class);
//        System.out.println(optinResponseDto);
//
//    }
}
