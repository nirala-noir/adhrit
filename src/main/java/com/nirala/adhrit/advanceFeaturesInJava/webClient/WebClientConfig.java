package com.meta.verse.advanceFeaturesInJava.webClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@Slf4j
public class WebClientConfig {
    @Value("${app.rcs.message.post.url}")
    private String rcsBaseUrl;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Bean("rcsWebClient")
    public WebClient rcsWebClient(){
        WebClient.Builder builder = webClientBuilder.clone();
        ClientHttpConnector connector= new ReactorClientHttpConnector(HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30 * 1000)

                .doOnConnected(c -> c.addHandlerLast(new ReadTimeoutHandler(30))
                        .addHandlerLast(new WriteTimeoutHandler(30))));

        return builder.baseUrl(rcsBaseUrl).clientConnector(connector).build();
    }
}
