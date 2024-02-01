package com.springWebFlux;

import com.springWebFlux.dto.InputFailedValidationResponse;
import com.springWebFlux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec06ExchangeTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    //exchange = retrieve + additional info like Http codes

    @Test
    public void blockTest() {
        Mono<Object> response = this.webClient.get().uri("reactiveMath/square/{input}/throw", 5)
                .exchangeToMono(resp -> exchange(resp))
                        .doOnNext(System.out::println);


        StepVerifier.create(response)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse cr){
        if(cr.statusCode() == HttpStatus.BAD_REQUEST){
            return cr.bodyToMono(InputFailedValidationResponse.class);
        }else{
            return cr.bodyToMono(Response.class);
        }
    }

}
