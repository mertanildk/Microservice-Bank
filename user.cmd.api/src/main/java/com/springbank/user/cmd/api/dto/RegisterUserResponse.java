package com.springbank.user.cmd.api.dto;


import lombok.AllArgsConstructor;



public class RegisterUserResponse extends BaseResponse {
    private String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
