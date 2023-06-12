package com.springbank.user.query.api.handlers;

import com.springbank.user.query.api.dto.UserLookUpResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import com.springbank.user.query.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserRepository userRepository;

    @Override
    @QueryHandler
    public UserLookUpResponse getUserById(FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        return user.map(UserLookUpResponse::new).orElse(null);
    }

    @Override
    @QueryHandler
    public UserLookUpResponse searchUsers(SearchUsersQuery query) {
        var users = userRepository.findByFiltersRegex(query.getFilter());
        return new UserLookUpResponse(users);
    }

    @Override
    @QueryHandler
    public UserLookUpResponse getAllUsers(FindAllUsersQuery query) {
        var users = userRepository.findAll();
        return new UserLookUpResponse(users);
    }
}
