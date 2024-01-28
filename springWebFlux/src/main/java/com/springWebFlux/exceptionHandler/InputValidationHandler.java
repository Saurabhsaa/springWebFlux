package com.springWebFlux.exceptionHandler;

import com.springWebFlux.dto.InputFailedValidationResponse;
import com.springWebFlux.exception.InputValidationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handler(InputValidationException ex){
        InputFailedValidationResponse validationResponse = new InputFailedValidationResponse();
        validationResponse.setErrorCode(ex.getErrorCode());
        validationResponse.setInput(ex.getInput());
        validationResponse.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(validationResponse);
    }

}
