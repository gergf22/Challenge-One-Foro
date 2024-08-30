package com.alura.foro.exceptions;

public class BadRequestException extends Exception{

    public BadRequestException(String message){
        super(message);
    }
}
