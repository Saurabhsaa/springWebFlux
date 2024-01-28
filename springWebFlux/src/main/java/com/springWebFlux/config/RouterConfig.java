package com.springWebFlux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterConfig {

    @Autowired
    RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter(){
        return RouterFunctions.route()
                .path("router",() -> serverResponseRouterFunction())
                .build();
    }

//    @Bean
    private RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("square/{input}", RequestPredicates.path("*/*/1?").or(RequestPredicates.path("*/*/20")) ,requestHandler :: squateHandler)
                .GET("square/{input}", req -> ServerResponse.badRequest().bodyValue("allowed range 10-19 "))
                .GET("table/{input}",request -> requestHandler.tableHandler(request))
                .GET("table/{input}/stream",request -> requestHandler.tableStreamHandler(request))
                .POST("multiply",request -> requestHandler.multiply(request))
                .GET("square/{input}/valid",request -> requestHandler.squateHandlerWithValidation(request))
                .build();
    }

}
