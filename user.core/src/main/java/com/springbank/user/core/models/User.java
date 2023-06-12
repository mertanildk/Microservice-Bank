package com.springbank.user.core.models;


import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotEmpty(message = "no first name was specified")
    private String firstName;
    @NotEmpty(message = "no last name was specified")
    private String lastName;
    @Email(message = "invalid email address")
    private String emailAddress;
    @NotNull(message = "no account was specified")
    @Valid
    private Account account;
}
