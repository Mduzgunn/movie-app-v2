package com.md.movieappv2.exception;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException(String s) {
        super(s);
    }
}
