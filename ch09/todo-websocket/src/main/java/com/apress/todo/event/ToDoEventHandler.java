package com.apress.todo.event;

import com.apress.todo.config.ToDoProperties;
import com.apress.todo.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(ToDo.class) //аннотация сообщает BeanPostProcessor о необходимости просмотреть соответствующий класс на предмет наличия методов-обработчиков
public class ToDoEventHandler { //Генератор отправляет STOMP-сообщение в тему при отправке нового запланированного дела с помощью HTTP-метода POST

    private Logger log = LoggerFactory.getLogger(ToDoEventHandler.class);
    private SimpMessagingTemplate simpMessagingTemplate; //реализация паттерна Template, используемая для отправки сообщений с помощью протокола STOMP
    private ToDoProperties toDoProperties; //Представляет собой пользовательский обработчик свойств. Описывает брокер (todo.ws.broker), конечную точку (todo.ws.endpoint) и конечную точку приложения для WebSockets

    public ToDoEventHandler(SimpMessagingTemplate simpMessagingTemplate,ToDoProperties toDoProperties){
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.toDoProperties = toDoProperties;
    }

    @HandleAfterCreate //Отмечает метод, получающий все события, происходящие после сохранения класса предметной области в базу данных
    public void handleToDoSave(ToDo toDo){
        this.simpMessagingTemplate.convertAndSend(this.toDoProperties.getBroker() + "/new", toDo);
        log.info(">> Sending Message to WS: ws://todo/new - " + toDo);
    }
}
