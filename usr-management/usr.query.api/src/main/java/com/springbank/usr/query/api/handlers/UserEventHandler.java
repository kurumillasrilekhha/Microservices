package com.springbank.usr.query.api.handlers;

import com.springbank.usr.core.events.UserRegisteredEvent;
import com.springbank.usr.core.events.UserRemovedEvent;
import com.springbank.usr.core.events.UserUpdatedEvent;

public  interface  UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
