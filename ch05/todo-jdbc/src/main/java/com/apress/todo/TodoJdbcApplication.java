package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoJdbcApplication.class, args);
	}
}
/**
 3 Spring JDBC
 Spring Boot использует автоконфигурацию для задания разумных настроек
 по умолчанию, если обнаруживает, что в приложении естьJAR-файлы JDBC.
 Spring Boot автоматически конфигурирует источник данных в соответствии
 с найденным по пути к классам SQL-драйвером. Если он обнаруживает на-
 личие любой встроенной СУБД (Н2, HSQL или Derby), то использует для
 нее настройки по умолчанию; в приложении может быть
 две зависимости для драйверов (например, MySQL и Н2) и если Spring Boot
 не найдет никакого объявления компонента для источника данных, то создаст
 его на основе найденного по пути к классам JAR-файла встроенной СУБД
 (например, Н2). Spring Boot также настраивает в качестве пула соединений
 по умолчанию HikariCP. Конечно, эти настройки по умолчанию можно переопределить.
 3.1 Работа с JDBC в Spring Boot
 # Пользовательский DataSource
 spring.datasource.username=springboot
 spring.datasource.password=rocks!
 spring.datasource.driver-class-name=com.mysql.jdbc.Driver
 spring.datasource.url=jdbc:mysql://localhost:3306/testdb?autoReconnect=
 true&useSSL=false
 spring.datasource.jndi-name=java:jboss/ds/todos
 3.2 Приложение ToDo с использованием JDBC
 Инициализация базы данных: schema.sql
 Если Spring Boot находит файлы schema.sql и/или data.sql, то автоматически их выполняет.
 Консоль Н2
 spring.h2.console.enabled=true активировать
 http://localhost:8080/h2-console перейти, чтобы увидеть консоль. URL JDBC должен быть jdbc:h2:mem:testdb
 logging.level.org.springframework.data=INFO Чтобы видеть, какие SQL-запросы выполняются в приложении
 logging.level.org.springframework.jdbc.core.JdbcTemplate=DEBUG
 */