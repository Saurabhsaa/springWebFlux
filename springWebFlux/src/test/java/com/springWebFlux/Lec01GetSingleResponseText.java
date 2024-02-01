package com.springWebFlux;

import com.springWebFlux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01GetSingleResponseText extends BaseTest{

    @Autowired
    private WebClient webClient;


    @Test
    public void blockTest(){
        Response response = this.webClient.get().uri("reactiveMath/square/{input}",5,10)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        System.out.println(response);

    }

    @Test
    public void test(){
        Mono<Response> response = this.webClient.get().uri("reactiveMath/square/{input}",5,10)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(response)
                .expectNextMatches(r -> r.getOutput() == 25)
                .verifyComplete();

    }

}
