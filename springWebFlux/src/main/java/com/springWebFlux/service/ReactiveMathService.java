package com.springWebFlux.service;

import com.springWebFlux.dto.MultiplyRequestDto;
import com.springWebFlux.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input){
        return Mono.fromSupplier(() -> new Response(input*input));
    }

    public Flux<Response> multiplictionTable(int input){
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
//                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reacitve math service processing : "+i))
                .map(i -> new Response(i*input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> requestDto){
        return requestDto.map(dto -> dto.getFirst()*dto.getSecond())
                .map(val -> new Response(val));
    }

}
