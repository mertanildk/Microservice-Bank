package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.springbank.user.cmd.api.dto.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/registerUser")
@RequiredArgsConstructor
public class RegisterUserController {
    private final CommandGateway commandGateway;


    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand command) {
        try {
            command.setId(UUID.randomUUID().toString());
            commandGateway.sendAndWait(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse("User successfully registered"));
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register user request for id - " + command.getId();
            System.out.println(e.fillInStackTrace());
            return ResponseEntity.internalServerError().body(new RegisterUserResponse(safeErrorMessage));
        }

    }
}
