package com.md.movieappv2.exception;

public class DirectorNotFoundException extends RuntimeException{
    public DirectorNotFoundException(String s) {
        super(s);
    }
}
