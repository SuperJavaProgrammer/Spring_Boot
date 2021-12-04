package com.apress.todo.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class ToDoErrorHandler implements ErrorHandler { //класс для проверки корректности данных, вызываемый для каждого сообщения
    private static Logger log = LoggerFactory.getLogger(ToDoErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        log.warn("ToDo error...");
        log.error(t.getCause().getMessage());
    }
}
