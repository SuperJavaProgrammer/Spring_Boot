package com.apress.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "todo") //используется совместно с @EnableConfigurationProperties(ToDoProperties.class)
public class ToDoProperties {
    private String path;
}
