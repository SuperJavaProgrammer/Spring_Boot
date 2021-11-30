package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoInMemoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoInMemoryApplication.class, args);
	}
}
/**
 1. Spring MVC
 Для работы с веб-технологиями во фреймворке Spring предназначены модули spring-web, spring-webmvc, spring webflux и spring-websocket. Всего 20 модулуй
 2. Автоконфигурация Spring Boot MVC
 spring-boot-starter-web
 Автоконфигурация добавляет в веб-приложение следующие возможности:
 Поддержка статического контента spring.mvc.static-path-pattern или spring.resources.static-locations.
 Если создать файл index.html, Spring Boot выдаст его автоматически без регистрации компонентов или потребности в дополнительной конфигурации
 Компонент HttpMessageConverters. Формат JSON выдается по умолчанию
 Сериализаторы и десериализаторы JSON spring.jackson.dateformat=yyyy-MM-dd
 Сопоставление с путем и согласование контента spring.mvc.contentnegotiation.favor-parameter=true, spring.mvc.contentnegotiation.parametername=myparam
 Обработка ошибок использует путь /error для создания страницы, на которой выводятся все глобальные ошибки
 Поддержка шаблонизаторов
 3. Spring Boot Web: приложение ToDo
 3.1 Приложение ToDo
 3.2 Тестирование: приложение ToDo
 curl -i http://localhost:8080/api/todo - посмотреть список
 http://localhost:8080/api/todo/9d4e4a62-f24f-44b6-a50b-733cc79a93ba - получить по айди
 curl -i -X POST -H "Content-Type: application/json" -d @json.txt http://localhost:8080/api/todo - в json.txt {"description":"Test7"} добавить новое дело
 curl -i -X PUT -H "Content-Type: application/json" -d @json2.txt http://localhost:8080/api/todo -  json2.txt {"description":"Test7Update","id":"bedd5738-1f76-44b0-bdb9-15a23cce8fde"} обновить дело
 curl -i -X PATCH http://localhost:8080/api/todo/9d4e4a62-f24f-44b6-a50b-733cc79a93ba - пометить сделанным
 curl -i -X DELETE http://localhost:8080/api/todo/9d4e4a62-f24f-44b6-a50b-733cc79a93ba - удалить
 curl -i -X POST -H "Content-Type: application/json" -d @jsonEmpty.txt http://localhost:8080/api/todo - {"description":"   "} - проверка валидации
 curl -i -X POST -H "Content-Type: application/json" http://localhost:8080/api/todo - ошибка от @ExceptionHandler, нет тела запроса
 Не работают:
 --- curl -i -X POST -H "Content-Type: application/json" -d'{"description":"Read the Pro Spring Boot 2nd Edition Book"}' http://localhost:8080/api/todo - добавить новую запись
 --- curl -i -X POST -H "Content-Type: application/json" -d \"{"description":"Read the Pro Spring Boot 2nd Edition Book"}\" http://localhost:8080/api/todo - если есть проблемы с кавычками, экранировать
 --- curl -s http://localhost:8080/api/todo | jq - отобразить в отформатированном виде
 --- curl -i -X PUT -H "Content-Type: application/json" -d \"{"description":"Take the dog and the cat for a walk","id":"0c850c25-25d2-4f73-8590-3ee92fa910be"}\" http://localhost:8080/api/todo - обновление
 4. Spring Boot Web: переопределение настроек по умолчанию
 4.1 Переопределение настроек сервера
 server.port=8081
 java -jar todo-in-memory-0.0.1-SNAPSHOT.jar --port=8787
 {server.port=${port:8282} - если вы передадите аргумент - - port, то Spring им воспользуется; если же нет — параметр будет равен 8282
 https://docs.spring.io/spring/docs/current/spnng-framework-reference/core.html#expressions. - SPEL
 server.address=10.0.0.7
 server.servlet.context-path=/my-todo-app
 {server.port=8443
 server.ssl.key-store=classpath:keystore.jks
 server.ssl.key-store-password=secret
 server.ssl.key-password=secret}
 {server.servlet.session.store-dir=/tmp
 server.servlet.session.persistent=true
 server.servlet.session.timeout=15
 server.servlet.session.cookie.name=todo-cookie.dat
 server.servlet.session.cookie.path=/tmp/cookies}
 server.http2.enabled=true
 4.2 Формат даты JSON
 spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
 spring.jackson.time-zone=MST7MDT
 https://docs.spring.io/spring-boot/docs/current/reference/html/common-applicationproperties.html
 4.3 Content-Type: JSON/XML
 <dependency>
 <groupld>com.fasterxml.jackson.dataformat</groupld>
 <artifactld>jackson-dataformat-xml</artifactld>
 </dependency>
 spring.mvc.contentnegotiation.favor-parametersrue
 curl -s http://localhost:8080/api/todo?format=xml
 curl -s http://localhost:8080/api/todo?format=json
 4.4 Spring MVC: переопределение настроек по умолчанию
 spring.mvc.view.prefix=/WEB-INF/my-views/
 spring.mvc.view.suffix=.jsp
 https://docs.spring.io/spring/docs/5.0.5.RELEASE/spring-framework-reference/web.html#mvc
 4.5 Использование другого контейнера приложения
 Использование Jetty Server
 <dependency>
 <groupld>org.springframework.boot</groupld>
 <artifactld>spring-boot-starter-web</artifactld>
 <exclusions>
 <exclusion>
 <groupld>org.springframework.boot</groupld>
 <artifactld>spring-boot-starter-tomcat</artifactld>
 </exclusion>
 </exclusions>
 </dependency>
 <dependency>
 <groupld>org.springframework.boot</groupld>
 <artifactld>spring-boot-starter-jetty</artifactld>
 </dependency>
 */