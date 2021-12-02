package com.apress.todo.config;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.apress.todo.repository") //для настройки всей необходимой для API реактивных потоков данных инфраструктуры. Важно также указать в ней местоположение репозиториев (не обязательно, если репозитории входят в состав пакета)
public class ToDoConfig extends AbstractReactiveMongoConfiguration { //для настройки встроенного сервера MongoDB необходимо расширить этот абстрактный класс, реализовав методы reactiveMongoClient и getDatabaseName

    private final Environment environment;

    public ToDoConfig(Environment environment){
        this.environment = environment;
    }

    @Override
    protected String getDatabaseName() {
        return "todos";
    }

    @Override //создает экземпляр MongoClient, подключающийся к порту, на котором работает встроенный сервер MongoDB (задается с помощью свойства среды local.mongo.port).
    @Bean
    @DependsOn("embeddedMongoServer") //для создания reactiveMongoClient после компонента embeddedMongoServer
    public MongoClient reactiveMongoClient() {
        int port = environment.getProperty("local.mongo.port", Integer.class);
        return MongoClients.create(String.format("mongodb://localhost:%d", port));
    }

    @Bean
    public CommandLineRunner insertAndView(ToDoRepository repository, ApplicationContext context){
        return args -> {

            repository.save(new ToDo("Do homework")).subscribe();
            repository.save(new ToDo("Workout in the mornings", true)).subscribe();
            repository.save(new ToDo("Make dinner tonight")).subscribe();
            repository.save(new ToDo("Clean the studio", true)).subscribe();

            repository.findAll().subscribe(System.out::println);
        };
    }
}
