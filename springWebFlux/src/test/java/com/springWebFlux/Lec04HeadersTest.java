package com.springWebFlux;

import com.springWebFlux.dto.MultiplyRequestDto;
import com.springWebFlux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec04HeadersTest extends BaseTest {

    @Autowired
    WebClient webClient;


    @Test
    public void postTest(){
        Mono<Response> responseMono = this.webClient.post()
                .uri("reactiveMath/multiply")
                .bodyValue(buildRequestDto(5,2))
                .headers(h -> h.set("someKey","someValue"))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();

    }

    private MultiplyRequestDto buildRequestDto(int a, int b){
        MultiplyRequestDto dto = new MultiplyRequestDto();
        dto.setFirst(a);
        dto.setSecond(b);
        return dto;
    }

}
