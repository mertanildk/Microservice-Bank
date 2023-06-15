package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.User;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no user details were specified")
    @Valid
    private User user;
}
