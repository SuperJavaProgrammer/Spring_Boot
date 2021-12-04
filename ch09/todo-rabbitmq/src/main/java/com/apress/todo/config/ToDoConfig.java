package com.apress.todo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToDoConfig {

    @Bean //Эта фабрика требуется при использовании аннотации @RabbitListener для пользовательских настроек, поскольку мы работаем с экземплярами ToDo; необходимо обязательно задать средство преобразования
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
            factory.setConnectionFactory(connectionFactory);
            factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){ //Вспомогательный класс, умеющий отправлять и получать сообщения. В данном случае необходимо настроить его для генерации объектов JSON с помощью преобразователя Jackson
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
            template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public Queue queueCreation(@Value("${todo.amqp.queue}") String queue){
        return new Queue(queue,true,false,false); //Можно создать очередь вручную, но в данном случае мы создаем ее программным образом: передаем название очереди, указываем, должна ли она быть устойчивой к перезагрузке сервера, применяться только данным соединением и автоматически уничтожаться в случае, если больше не используется
    }

    //This Code is when you have a different domain class like my.client.MyTodo, that matches the same
    //JSON structure but you don't have access to the source domain.
    //__TypeId__ is mandatory to describe the domain class to be converted.
    /*
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        Jackson2JavaTypeMapper mapper = new DefaultJackson2JavaTypeMapper() {

            @Override
            public JavaType toJavaType(MessageProperties properties) {
                properties.setHeader("__TypeId__", "my.client.MyToDo");
                return super.toJavaType(properties);
            }

        };
        converter.setJavaTypeMapper(mapper);

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
    */
}
