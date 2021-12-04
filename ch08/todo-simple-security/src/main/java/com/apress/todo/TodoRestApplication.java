package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoRestApplication.class, args);
	}
}

/**
 Обеспечение безопасности с помощью Spring Boot
 spring-boot-starter-security
 Using generated security password: 2a569843-122a-4559-a245-60f5ab2b6c51
 при входе надо получить пароль и использовать его. Имя user
 spring.security.user.name=apress - переопределения настроек по умолчанию
 spring.security.user.password=springboot2
 spring.security.user.roles=ADMIN,USER
 */
