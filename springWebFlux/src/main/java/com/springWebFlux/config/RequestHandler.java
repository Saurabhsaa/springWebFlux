package com.springWebFlux.config;

import com.springWebFlux.dto.MultiplyRequestDto;
import com.springWebFlux.dto.Response;
import com.springWebFlux.exception.InputValidationException;
import com.springWebFlux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

    @Autowired
    private ReactiveMathService mathService;

    public Mono<ServerResponse> squateHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> responseMono = this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono,Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseMono = this.mathService.multiplictionTable(input);
        return ServerResponse.ok().body(responseMono,Response.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseMono = this.mathService.multiplictionTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseMono,Response.class);
    }

    public Mono<ServerResponse> multiply(ServerRequest serverRequest){
        Mono<MultiplyRequestDto>  requestDtoMono = serverRequest.bodyToMono(MultiplyRequestDto.class);
        Mono<Response> responseMono = this.mathService.multiply(requestDtoMono);
        return ServerResponse.ok()
                .body(responseMono,Response.class);
    }

    public Mono<ServerResponse> squateHandlerWithValidation(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if(input<10 || input > 20){
            return Mono.error(new InputValidationException(input));
        }
        Mono<Response> responseMono = this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono,Response.class);
    }

}
