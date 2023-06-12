package com.springbank.user.cmd.api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponse extends BaseResponse {
    private String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
