package com.springWebFlux.exception;

public class InputValidationException extends RuntimeException{
    private static int errorCode = 100;
    private static final String MSG = "allowed range is 10-20";
    private final int input;

    public InputValidationException(int input){
        super(MSG);
        this.input = input;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getInput() {
        return input;
    }
}
