package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.springbank.user.cmd.api.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/updateUser")
@RequiredArgsConstructor
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id, @Valid @RequestBody UpdateUserCommand command) {

        try {
            command.setId(id);

            commandGateway.send(command);
            return ResponseEntity.ok(new BaseResponse("User successfully updated!"));
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing update user request for id - " + id;
            System.out.println(e);
            return ResponseEntity.internalServerError().body(new BaseResponse(safeErrorMessage));
        }
    }
}
