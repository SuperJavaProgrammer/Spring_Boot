package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoWebsocketApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoWebsocketApplication.class, args);
	}
}

/**
 WebSockets — новый способ связи, заменяющий веб-технологию «клиент/сервер». Он делает возможным длительные
 односокетные ТСР-соединения между клиентом и сервером. Его также называют технологией проталкивания (push), поскольку
 сервер может отправлять данные без необходимости выполнения клиентом долгих опросов для запроса обновлений.
 */