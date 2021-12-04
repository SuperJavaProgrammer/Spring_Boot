package com.apress.todo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "todo.jms")
public class ToDoProperties { //для хранения свойства todo. jms.destination, указывающего, к какой очереди/теме необходимо подключаться
    private String destination;
}
