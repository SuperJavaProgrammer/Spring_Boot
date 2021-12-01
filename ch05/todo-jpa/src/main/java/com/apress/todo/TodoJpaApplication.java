package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoJpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoJpaApplication.class, args);
	}
}

/**
 4. Spring Data JPA
 Необходимо только создать интерфейс, расширяющий Repository<T,ID>, CrudRepository<T,ID> или JpaRepository<T, ID>
 CrudRepository<T,ID>, где Т означает сущность (класс модели предметной области), a ID — первичный ключ,
 который должен реализовать интерфейс Serializable.
 Для использования Spring Data JPA со Spring Boot необходимы зависимость spring-boot-starter-data-jpa и SQL-драйвер
 Когда Spring Boot производит автоконфигурацию и обнаруживает наличие
 JAR-файлов Spring Data JPA, он настраивает источник данных по умолчанию (если ни один не описан).
 Настраивает поставщик JPA (по умолчанию используется Hibernate), активирует репозитории (с помощью аннотации
 @EnableJpaRepositories). Проверяет, описаны ли какие-нибудь методы запросов.

 create — создает схему и удаляет предыдущие данные;
 create-drop — создает, а затем и удаляет схему в конце сеанса;
 update — обновляет схему при необходимости;
 validate — проверяет схему без внесения каких-либо изменений в базе данных;
 попе — отключает обработку DDL.
 */
