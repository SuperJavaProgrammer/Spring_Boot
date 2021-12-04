package com.apress.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "todo.ws")
public class ToDoProperties { //для хранения информации о конечных точках //вспомогательный класс для хранения информации о брокере (/stomp) и о том, куда подключается веб-клиент (тема — /todo/new)
    private String app = "/todo-api-ws";
    private String broker = "/todo";
    private String endpoint = "/stomp";
}
