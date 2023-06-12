package com.springbank.user.query.api.queries;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class FindUserByIdQuery {
    @Id
    private String id;
}
