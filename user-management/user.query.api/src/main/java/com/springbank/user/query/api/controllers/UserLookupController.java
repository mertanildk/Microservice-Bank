package com.springbank.user.query.api.controllers;

import com.springbank.user.core.messages.ResponseMessages;
import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userLookup")
@RequiredArgsConstructor
public class UserLookupController {
    private final QueryGateway queryGateway;

    @GetMapping("/")
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        try {
            var query = new FindAllUsersQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().body(new UserLookupResponse(ResponseMessages.USERS_FAILED_FOR_ALL));
        }
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindUserByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().body(new UserLookupResponse(ResponseMessages.USER_FAILED_BY_ID));
        }
    }

    @GetMapping("byFilter/{filter}")
    public ResponseEntity<UserLookupResponse> searchUserByFilter(@PathVariable(value = "filter") String filter) {
        try {
            var query = new SearchUsersQuery(filter);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().body(new UserLookupResponse(ResponseMessages.USERS_FAILED_BY_FILTERS));
        }
    }

}
