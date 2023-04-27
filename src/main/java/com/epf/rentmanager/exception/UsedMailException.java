package com.epf.rentmanager.exception;

public class UsedMailException extends Exception {

    public UsedMailException(){
    super();
    }

    public UsedMailException(String message){
        super(message);
    }
}
