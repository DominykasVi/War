package com.war;

public class IncorrectValuePassedException extends Exception{
    IncorrectValuePassedException(){};

    IncorrectValuePassedException(String message){
        super(message);
    }
}
