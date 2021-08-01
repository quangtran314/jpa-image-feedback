package com.amela.exception;

public class BadWordException extends Exception{
    @Override
    public String getMessage() {
        return "Message contains bad word";
    }
}
