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
 5. Spring Data REST
 Для использования Spring Data REST в обычном приложении Spring
 MVC необходимо инициировать его настройку, включив класс
 RepositoryRestMvcConfiguration с помощью аннотации @Import в классе JavaConfig
 (в том, где находится аннотация @Configuration); но при непосредственном
 использовании Spring Boot ничего этого делать не нужно. Spring Boot берет
 на себя все эти заботы благодаря аннотации @EnableAutoConfiguration.
 Для использования Spring Data REST в приложении Spring Boot необходимо
 включить в него зависимости для технологий spring-boot-starter-data-rest
 и spring-boot-starter-data-* и/или SQL-драйвер, если вы собираетесь за
 действовать SQL СУБД.
 */
