package com.springbank.user.query.api.handlers;

import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import com.springbank.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserRepository userRepository;

    @Override
    @QueryHandler
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        return user.map(UserLookupResponse::new).orElse(null);
    }

    @Override
    @QueryHandler
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        var users = new ArrayList<>(userRepository.findByFiltersRegex(query.getFilter()));
        return new UserLookupResponse(users);
    }

    @Override
    @QueryHandler
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        var users = userRepository.findAll();
        return new UserLookupResponse(users);
    }
}
