package com.springWebFlux;

import com.springWebFlux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02GetMultiResponseTest extends BaseTest {

    @Autowired
    WebClient webClient;


    @Test
    public void fluxTest(){
        Flux<Response> response = this.webClient.get()
                .uri("reactiveMath/table/{input}",5,10)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext((i)-> System.out.println(i));


        StepVerifier.create(response)
                .expectNextCount(10)
                .verifyComplete();

    }

    @Test
    public void fluxStreamTest(){
        Flux<Response> response = this.webClient.get()
                .uri("reactiveMath/table/{input}/stream",2)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext((i)-> System.out.println(i));


        StepVerifier.create(response)
                .expectNextCount(10)
                .verifyComplete();

    }

}
