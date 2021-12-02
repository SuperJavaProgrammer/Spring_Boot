package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoWebfluxApplication.class, args);
	}
}
/**
 3. WebFlux
 Одна из главных возможностей Spring WebFlux — две модели программирования
 Снабженные аннотациями контроллеры.
 Функциональные конечные точки.

 WebFlux и автоконфигурация Spring Boot
 Для полного контроля над автоконфигурацией WebFlux необходимо добавить
 свой собственный класс @Configuration, снабженный аннотацией ©EnableWebFlux
 spring-boot-starter-webflux
 */