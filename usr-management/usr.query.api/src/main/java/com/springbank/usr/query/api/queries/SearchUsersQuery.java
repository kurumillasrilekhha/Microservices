package com.springbank.usr.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUsersQuery {
    private String filter;
}

