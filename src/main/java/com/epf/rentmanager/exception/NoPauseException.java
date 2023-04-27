package com.epf.rentmanager.exception;

public class NoPauseException extends Exception {

    public NoPauseException(){
    super();
    }

    public NoPauseException(String message){
        super(message);
    }
}
