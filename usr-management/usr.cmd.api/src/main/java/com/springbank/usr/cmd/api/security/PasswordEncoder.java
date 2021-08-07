package com.springbank.usr.cmd.api.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
