package com.apress.todo.config;

import com.apress.todo.domain.ToDo;
import com.apress.todo.rmq.ToDoProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableScheduling
@Configuration
public class ToDoSender { //класс, который будет отправлять сообщения ToDo

    @Bean
    public CommandLineRunner sendToDos(@Value("${todo.amqp.queue}") String destination, ToDoProducer producer){
        return args -> {
            producer.sendTo(destination,new ToDo("workout tomorrow morning!"));
        };
    }

    //Отправка сообщений через промезжуток времени
    /*
    @Autowired
    private ToDoProducer producer;
    @Value("${todo.amqp.queue}")
    private String destination;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000L) //указывает интерфейсу Taskscheduler выполнять метод sendToDos через фиксированный отрезок времени 1000 миллисекунд. Это значит, что каждую секунду в очередь будет отправляться сообщение
    private void sendToDos(){
        producer.sendTo(destination,new ToDo("Thinking on Spring Boot at " + dateFormat.format(new Date())));
    }
    */
}
