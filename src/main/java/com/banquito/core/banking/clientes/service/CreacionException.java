package com.banquito.core.banking.clientes.service;

public class CreacionException extends RuntimeException {

    public CreacionException(String message){
        super(message);
    }
    
    public CreacionException(String message, Exception cause){
        super(message, cause);
    }

}
