package com.apress.reactor.example;

import com.apress.reactor.example.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Configuration
public class MonoExample {

    static private Logger LOG = LoggerFactory.getLogger(MonoExample.class);

    @Bean //Spring Boot выполняет этот компонент после завершения связывания всех компонентов Spring воедино, но до запуска приложения; это удобный способ выполнения кода (например, инициализации) до запуска приложения
    public CommandLineRunner runMonoExample(){
        return args -> {

            MonoProcessor<ToDo> promise = MonoProcessor.create();
            /**
             Reactor существует понятие узлов-обработчиков — разновидностей
             издателей, являющихся также подписчиками; это значит, что
             можно не только подписаться на узел-обработчик, но и вызывать методы
             для внедрения данных в последовательность вручную или для ее завершения.
             В данном случае мы используем метод onNext для выдачи экземпляра ToDo
             */
            Mono<ToDo> result = promise
                    .doOnSuccess(p -> LOG.info("MONO >> ToDo: {}", p.getDescription())) //метод вызывается или срабатывает при успешном завершении Mono
                    .doOnTerminate( () -> LOG.info("MONO >> Done")) //метод вызывается или срабатывает, когда выполнение Mono прерывается либо успешным завершением, либо ошибкой
                    .doOnError(t -> LOG.error(t.getMessage(), t)) //метод вызывается при завершении Mono ошибкой
                    .subscribeOn(Schedulers.single()); //Подписывается на тип Mono и запрашивает неограниченные требования на указанного исполнителя (worker) Scheduler
//            Mono - Издатель реактивного потока данных, поддерживающий основные операции, который завершается либо успешной выдачей элемента, либо ошибкой.

                    promise.onNext(new ToDo("Buy my ticket for SpringOne Platform 2018")); //метод выдает значение, которое может быть помечено аннотацией @Nullable.
            //promise.onError(new IllegalArgumentException("There is an error processing the ToDo..."));

            result.block(Duration.ofMillis(1000)); //Подписывается на тип Mono и осуществляет блокировку до момента получения следующего сигнала или истечения времени ожидания
        };
    }
}
