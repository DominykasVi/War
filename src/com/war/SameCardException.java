package com.war;

public class SameCardException extends Exception{
    public SameCardException(){}

    public SameCardException(String message){
        super(message);
    }
}
