package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
        scanBasePackages = {"com.apress"},
        exclude = HibernateJpaAutoConfiguration.class)
public class TodoRest2dsApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoRest2dsApplication.class, args);
	}
}
/**
 Дополнительные возможности по работе с данными с помощью Spring Boot
 Spring Boot обеспечивает автоконфигурацию по умолчанию,
 на основе пути к классам, причем ее можно переопределить без всяких
 проблем. Для использования нескольких экземпляров Datasource, возможно
 указывающих на различные базы данных и/или различные СУБД, необходи
 мо переопределить настройки по умолчанию.
 Datasource, EntityManager, TransactionManager, JpaVendor и т. д.
 Для использования нескольких источников данных необходимо добавить
 такую же конфигурацию, следует добавить в настройки
 несколько EntityManager, TransactionManager и т. д.
 Spring Boot реализует паттерн «Шаблонный метод» для скрытия «под капотом» всех сложных задач,
 обычно необходимых без фреймворка Spring.
 */
