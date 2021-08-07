package com.springbank.usr.query.api.handlers;

import com.springbank.usr.query.api.dto.UserLookupResponse;
import com.springbank.usr.query.api.queries.FindAllUsersQuery;
import com.springbank.usr.query.api.queries.FindUserByIdQuery;
import com.springbank.usr.query.api.queries.SearchUsersQuery;
public interface  UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
