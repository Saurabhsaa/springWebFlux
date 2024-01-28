package com.springWebFlux.controller;

import com.springWebFlux.dto.MultiplyRequestDto;
import com.springWebFlux.dto.Response;
import com.springWebFlux.service.MathService;
import com.springWebFlux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("reactiveMath")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService mathService;


    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input){
        return this.mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return this.mathService.multiplictionTable(input);
    }

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input){
        return this.mathService.multiplictionTable(input);
    }

    @PostMapping("multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> multiplyRequestDto){
        return this.mathService.multiply(multiplyRequestDto);
    }

}
