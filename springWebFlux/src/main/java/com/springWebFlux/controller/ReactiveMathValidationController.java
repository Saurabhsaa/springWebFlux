package com.springWebFlux.controller;

import com.springWebFlux.dto.Response;
import com.springWebFlux.exception.InputValidationException;
import com.springWebFlux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactiveMath")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService mathService;


    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input){
        if(input<10 || input >20)
            throw new InputValidationException(input);

        return this.mathService.findSquare(input);
    }

    @GetMapping("square/{input}/monoError")
    public Mono<Response> monoError(@PathVariable int input){
        return Mono.just(input)
                .handle((integer, syncSink) ->{
                    if(integer>=10 && integer <=20){
                        syncSink.next(integer);
                    }else{
                        syncSink.error(new InputValidationException(integer));
                    }
                } ).cast(Integer.class)
                .flatMap(integer -> this.mathService.findSquare(integer));
    }



}
