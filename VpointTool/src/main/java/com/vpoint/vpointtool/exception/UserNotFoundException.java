package com.vpoint.vpointtool.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String staffId) {
        super(String.format(staffId + " user not found"));
    }
}
