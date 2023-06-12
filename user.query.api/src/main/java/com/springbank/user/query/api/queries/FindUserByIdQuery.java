package com.springbank.user.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class FindUserByIdQuery {
    @Id
    private String id;
}
