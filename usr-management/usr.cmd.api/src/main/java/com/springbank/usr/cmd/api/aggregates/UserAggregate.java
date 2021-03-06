package com.springbank.usr.cmd.api.aggregates;

import com.springbank.usr.cmd.api.commands.RegisterUserCommand;
import com.springbank.usr.cmd.api.commands.RemoveUserCommand;
import com.springbank.usr.cmd.api.commands.UpdateUserCommand;
import com.springbank.usr.cmd.api.security.PasswordEncoder;
import com.springbank.usr.cmd.api.security.PasswordEncoderImpl;
import com.springbank.usr.core.events.UserRegisteredEvent;
import com.springbank.usr.core.events.UserRemovedEvent;
import com.springbank.usr.core.events.UserUpdatedEvent;
import com.springbank.usr.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();
         System.out.println("BEFORE AGGREGATE LIFE CYCLE");

        AggregateLifecycle.apply(event);
        System.out.println("AFTER AGGREGATE LIFE CYCLE");
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new UserRemovedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {

        this.id = event.getId();
        this.user = event.getUser();

    }


    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
       this.id=event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
