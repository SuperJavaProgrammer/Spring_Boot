package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//нужно запустить сервер Rabbitmq
@SpringBootApplication
public class TodoRabbitmqApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoRabbitmqApplication.class, args);
	}
}
