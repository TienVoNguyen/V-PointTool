package com.vpoint.vpointtool.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String mess) {
        super(String.format(mess + " not found"));
    }
}
