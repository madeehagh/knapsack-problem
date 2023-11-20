package com.mobiquity.exception;

public class FileParserException extends RuntimeException{

    public FileParserException(String message, Exception e) {
        super(message, e);
    }

    public FileParserException(String message){
        super(message);
    }
}
