package com.springWebFlux.config;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class CalculatorHandler {

    public Mono<ServerResponse> additionHandlers(ServerRequest request){
        return process(request,(a,b)->ServerResponse.ok().bodyValue(a+b));
    }

    public Mono<ServerResponse> substractionHandlers(ServerRequest request){
        return process(request,(a,b)->ServerResponse.ok().bodyValue(a-b));
    }

    public Mono<ServerResponse> divisionHandlers(ServerRequest request){
        return process(request,(a,b)->ServerResponse.ok().bodyValue(a/b));
    }

    public Mono<ServerResponse> multiplicationHandlers(ServerRequest request){
        return process(request,(a,b)->{
            return b!= 0 ? ServerResponse.ok().bodyValue(a*b) : ServerResponse.badRequest().bodyValue("b can not be 0");
        });
    }

    private Mono<ServerResponse> process(ServerRequest request,
                                         BiFunction<Integer,Integer,Mono<ServerResponse>> opLogic){
        int a = getValue(request,"a");
        int b = getValue(request,"b");
        return opLogic.apply(a,b);
    }

    private int getValue(ServerRequest request,String key){
        return Integer.parseInt(request.pathVariable(key));
    }

}
