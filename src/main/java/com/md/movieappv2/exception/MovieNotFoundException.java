package com.md.movieappv2.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException (String s){
        super(s);
    }
}
