package com.apress.todo.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class ToDoSecurity {
    private PasswordEncoder encoder;

    public ToDoSecurity() { }
    public ToDoSecurity(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

}
