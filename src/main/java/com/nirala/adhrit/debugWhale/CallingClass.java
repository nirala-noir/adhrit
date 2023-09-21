package com.meta.verse.debugWhale;

import com.meta.verse.debugWhale.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class CallingClass {

    RestTemplate restTemplate = new RestTemplate();
    Product funcTemp() {
        try {
            String url = "http://localhost:8080/api/v1/getOptin";
            String completeUrl= UriComponentsBuilder.fromHttpUrl(url).queryParam("appId",10).queryParam("msisdn","raman").build().toUriString();
            return restTemplate.getForObject("http://localhost:8080/api/v1/getOptin?appId=10&msisdn=raman", Product.class);
        } catch (Exception e) {
            //PullPlatformReceiverErrorLogger.INSTANCE.getLogger().error("YuloreNumberFilterService::isNumberExistInDocker::ERROR WHILE VALIDATING MSISDN IN DOCKER FITERING TOOL,ERROR::", e);

        }
        return null;
    }
}
