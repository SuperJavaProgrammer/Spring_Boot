package com.apress.todo.rmq;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ToDoConsumer { //класс потребителя — прослушиватель для заданной очереди

    private Logger log = LoggerFactory.getLogger(ToDoConsumer.class);
    private ToDoRepository repository;

    public ToDoConsumer(ToDoRepository repository){
        this.repository = repository;
    }

    @RabbitListener(queues = "${todo.amqp.queue}") //Помечает метод(ее можно использовать и на уровне класса) для создания обработчика входящих сообщений в том смысле, что создает прослушиватель, подключенный к очереди RabbitMQ и передающий эти сообщения данному методу
    public void processToDo(ToDo todo){
        log.info("Consumer> " + todo);
        log.info("ToDo created> " + this.repository.save(todo));
    }
}
