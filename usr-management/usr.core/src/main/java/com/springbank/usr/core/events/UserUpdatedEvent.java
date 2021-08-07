package com.springbank.usr.core.events;

import com.springbank.usr.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {
    private String id;
    private User user;
}
