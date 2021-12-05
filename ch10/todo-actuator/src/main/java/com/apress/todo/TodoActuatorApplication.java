package com.apress.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoActuatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoActuatorApplication.class, args);
	}
}

/**
 Модуль Actuator, обеспечивающий проверку соблюдения нефункциональных требований к приложению.
 Модуль Spring Boot Actuator предоставляет готовые возможности мониторинга метрик и аудита.
 По умолчанию модуль Actuator открывает три доступные для посещения конечные точки.
 /actuator/health - конечная точка предоставляет доступ к основной информации о состоянии приложения {"status": "UP"}
 /actuator/info - отображает всю общедоступную информацию приложения
 /actuator - Префикс для всех конечных точек актуатора. Если обратиться к ней, можно получить гипермедиастраницу со списком всех остальных конечных точек
 /actuator/conditions - отображает отчет об автоконфигурации. В нем можно видеть группы: positiveMatches и negativeMatches и unconditionalClasses
 /actuator/beans - выводит список всех используемых в приложении компонентов Spring
 /actuator/configprops - выводит список всех свойств конфигурации, задаваемых компонентами @ConfigurationProperties
 /actuator/threaddump - Эта конечная точка запускает дамп потоков выполнения приложения. Она выводит все работающие потоки и их трассу вызовов в выполняющей приложение JVM
 /actuator/env - раскрывает все свойства из интерфейса ConfigurableEnvironment Spring. В результате демонстрируются все активные профили и системные переменные среды, а также все свойства приложения, включая свойства Spring Boot
 /actuator/loggers - отображает список всех средств журналирования в системе
 /actuator/loggers/{name} - можно посмотреть на конкретный пакет и его уровень журналирования
 /actuator/metrics - демонстрирует информацию о метриках текущего приложения: сколько оно задействует оперативной памяти, сколько памяти свободно, время непрерывной работы приложения, размер используемой кучи, число применяемых потоков выполнения и т. д. Добавить название в конец - http://localhost:8080/actuator/metrics/jvm.memory.max
 /actuator/mappings - выводит все списки всех объявленных в приложении путей @RequestMapping. Это очень удобно, если нужно узнать, какие объявлены отображения
 /actuator/shutdown - позволяет аккуратно завершить работу приложения
 /actuator/httptrace - выводит информацию о трассе вызовов, то есть обычно о нескольких последних HTTP-запросах. С ее помощью можно просматривать всю информацию как запроса, так и возвращаемую в ответ, для отладки приложения на уровне HTTP

 @Endpoint - создать свою конечную точку актуатора, для класса
 @ReadOperation, @WriteOperation @DeleteOperation - для методов - При применении посредством веб-доступа пользовательских конечных точек актуатора каждой операции соответствует свой HTTP-метод: @ReadOperation(Http.GET), @WriteOperation(Http.POST) и @DeleteOperation(Http.DELETE)
 @JmxEndpoint - если конечная точка должна быть доступна только через JMX
 @WebEndpoint - если нужен только веб-доступ
 */