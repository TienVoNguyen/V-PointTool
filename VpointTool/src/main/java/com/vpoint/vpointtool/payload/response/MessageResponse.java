package com.vpoint.vpointtool.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class MessageResponse {
    @NotNull(message = "message cannot null")
    private String message;
}
