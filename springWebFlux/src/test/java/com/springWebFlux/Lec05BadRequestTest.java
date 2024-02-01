package com.springWebFlux;

import com.springWebFlux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec05BadRequestTest extends BaseTest{

    @Autowired
    private WebClient webClient;


    @Test
    public void blockTest() {
        Mono<Response> response = this.webClient.get().uri("reactiveMath/square/{input}/throw", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

//        StepVerifier.create(response)
//                .expectNextCount(1)
//                .verifyComplete();

        StepVerifier.create(response)
                .verifyError(WebClientResponseException.BadRequest.class);
    }
}
