package com.apress.todo.config;

import com.apress.todo.error.ToDoErrorHandler;
import com.apress.todo.validator.ToDoValidator;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableConfigurationProperties(ToDoProperties.class)
public class ToDoConfig {

    @Bean
    public MessageConverter jacksonJmsMessageConverter() { //интерфейс продвигает создание реализаций методов toMessage и fromMessage, упрощающих подключение любых нужных сериализаций/преобразований
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
            converter.setTargetType(MessageType.TEXT);
            converter.setTypeIdPropertyName("_class_");
        return converter;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory, //Этот интерфейс требует регистрации компонента с конфигурацией средства проверки корректности данных (класса ToDoValidator)
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            factory.setErrorHandler(new ToDoErrorHandler()); //удалить, если не хотите выполнять проверку корректности данных
        configurer.configure(factory, connectionFactory);
        return factory;
    }

//    @Bean //пользовательский контейнер прослушивателя можно настроить программным образом
//    public DefaultMessageListenerContainer jmsListenerContainerFactory() {
//        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
//        dmlc.setPubSubDomain(true);
//        // Прочие настройки ...
//        return dmlc;
//    }

    @Configuration
    static class MethodListenerConfig implements JmsListenerConfigurer{ //выполнять проверку корректности данных, можно удалить, если не надо

        @Override
        public void configureJmsListeners (JmsListenerEndpointRegistrar jmsListenerEndpointRegistrar){
            jmsListenerEndpointRegistrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
        }

        @Bean
        public DefaultMessageHandlerMethodFactory myHandlerMethodFactory () {
            DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
                factory.setValidator(new ToDoValidator());
            return factory;
        }
    }
}
