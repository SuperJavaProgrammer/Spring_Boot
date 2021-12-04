package com.apress.todo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //аннотация использует автоконфигурацию для создания всех артефактов, необходимых для обмена сообщениями через WebSockets с помощью брокера, на основе весьма высокоуровневого субпротокола обмена сообщениями
@EnableConfigurationProperties(ToDoProperties.class)
public class ToDoConfig implements WebSocketMessageBrokerConfigurer { //Его методы переопределяются в нашем классе конфигурации для настройки протоколов и конечных точек под наши нужды

    private ToDoProperties props;

    public ToDoConfig(ToDoProperties props){
        this.props = props;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //Регистрирует протокол STOMP; в данном случае он регистрирует конечную точку /stomp, используя JavaScript-библиотеку SockJS
        registry.addEndpoint(props.getEndpoint()).setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { //Выполняет настройку опций брокера сообщений. В данном случае активирует брокер в конечной точке /todo. Это значит, что клиентам для использования брокера WebSockets необходимо подключаться к /todo
        config.enableSimpleBroker(props.getBroker());
        config.setApplicationDestinationPrefixes(props.getApp());
    }

}
