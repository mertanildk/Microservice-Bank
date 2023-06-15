package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.springbank.user.core.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/removeUser")
@RequiredArgsConstructor
public class RemoveUserController {

    private final CommandGateway commandGateway;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable(value = "id") String id) {

        try {
            commandGateway.send(new RemoveUserCommand(id));
            return ResponseEntity.ok(new BaseResponse("User successfully removed!"));
        } catch (Exception e) {
            String safeErrorMessage = "Error while processing remove user request for id - " + id;
            System.out.println(e);
            return ResponseEntity.internalServerError().body(new BaseResponse(safeErrorMessage));
        }
    }
}
