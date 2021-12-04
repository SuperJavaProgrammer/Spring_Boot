package com.apress.todo.rmq;

import com.apress.todo.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component //отмечает класс как кандидат в компоненты для последующего автоматического обнаружения контейнером Spring
public class ToDoProducer { //класс генератор, который будет отправлять сообщения в точку обмена (по умолчанию — прямая)

    private static final Logger log = LoggerFactory.getLogger(ToDoProducer.class);
    private RabbitTemplate template; //вспомогательный класс, упрощающий синхронное/асинхронное обращение к RabbitMQ для отправки и/или получения сообщений

    public ToDoProducer(RabbitTemplate template){
        this.template = template;
    }

    public void sendTo(String queue, ToDo toDo){ //В списке параметров этого метода — ключ маршрутизации и само сообщение. В данном случае роль ключа маршрутизации играет название очереди
        this.template.convertAndSend(queue, toDo);
        log.info("Producer> Message Sent");
    }
}
