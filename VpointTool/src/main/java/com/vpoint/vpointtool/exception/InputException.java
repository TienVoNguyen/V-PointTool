package com.vpoint.vpointtool.exception;

public class InputException extends RuntimeException {
    public InputException(String mess) {
        super(String.format(mess + " Input error"));
    }
}
