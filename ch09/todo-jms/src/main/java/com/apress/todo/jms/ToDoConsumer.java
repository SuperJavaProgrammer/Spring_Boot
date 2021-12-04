package com.apress.todo.jms;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ToDoConsumer { //класс потребителя, прослушивающий на предмет входящих сообщений из очереди ActiveMQ

    private Logger log = LoggerFactory.getLogger(ToDoConsumer.class);
    private ToDoRepository repository;

    public ToDoConsumer(ToDoRepository repository){
        this.repository = repository;
    }

    @JmsListener(destination = "${todo.jms.destination}",containerFactory = "jmsFactory") //@JmsListener - чтобы метод обрабатывал все поступающие из очереди сообщения. destination уточняет, к какой очереди/теме подключаться. containerFactory создается в качестве части конфигурации
    public void processToDo(@Valid ToDo todo){ //@Valid - для проверки любого из полей модели предметной области
        log.info("Consumer> " + todo);
        log.info("ToDo created> " + this.repository.save(todo));
    }
}
