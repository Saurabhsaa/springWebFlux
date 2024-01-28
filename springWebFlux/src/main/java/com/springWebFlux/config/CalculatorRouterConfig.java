package com.springWebFlux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class CalculatorRouterConfig {

    @Autowired
    CalculatorHandler calculatorHandler;

    @Bean
    public RouterFunction<ServerResponse> highLevelRouterCalculator(){
        return RouterFunctions.route()
                .path("calculator",() -> serverResponseRouterFunction())
                .build();

    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                .GET("{a}/{b}",isOperations("+") ,request -> calculatorHandler.additionHandlers(request))
                .GET("{a}/{b}",isOperations("-"),request -> calculatorHandler.substractionHandlers(request))
                .GET("{a}/{b}",isOperations("*"),request -> calculatorHandler.multiplicationHandlers(request))
                .GET("{a}/{b}",isOperations("/"),request -> calculatorHandler.divisionHandlers(request))
                .build();

    }

    private RequestPredicate isOperations(String operation){
        return RequestPredicates.headers(headers -> operation.equals(headers.asHttpHeaders()
                .toSingleValueMap().get("OP")));

    }

}
