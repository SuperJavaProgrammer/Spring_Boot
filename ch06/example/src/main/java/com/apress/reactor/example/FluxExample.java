package com.apress.reactor.example;

import com.apress.reactor.example.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

//@Configuration
public class FluxExample {

    static private Logger LOG = LoggerFactory.getLogger(FluxExample.class);

//    @Bean
    public CommandLineRunner runFluxExample(){
        return args -> {
            EmitterProcessor<ToDo> stream = EmitterProcessor.create();
            /**
            узел-обработчик — разновидность издателя;
             в данном случае мы используем синхронный узел-обработчик, способный
            «проталкивать» данные как посредством действий пользователя, так и путем
            подписки на расположенный ранее по конвейеру издатель и синхронного
            «вытягивания» из него данных. Этот узел-обработчик создает Flux из
            экземпляров ToDo; он предоставляет реализацию основанного на RingBuffer
            передающего сообщения узла-обработчика, реализующего обмен сообщения
            ми по типу «публикация/подписка» с синхронными циклами «вытягивания»
            данных
             */
            // Log values passing through the Flux and capture the first coming signal
            Mono<List<ToDo>> promise = stream
                    .filter( s -> s.isCompleted()) //используем фильтр с помощью вычисления предиката и выдачи значения в случае успеха (то есть если ToDo завершен)
                    .doOnNext(s -> LOG.info("FLUX >>> ToDo: {}", s.getDescription())) //Срабатывает, когда Flux выдает значение
                    .collectList() //Собирает все выдаваемые Flux элементы в список, выдаваемый итоговым Mono при завершении этой последовательности
                    .subscribeOn(Schedulers.single()); //Подписывается на данный Flux на основе потока-исполнителя Scheduler

            // Publish a value
            stream.onNext(new ToDo("Read a Book",true)); //Выдает во Flux начальное значение.
            stream.onNext(new ToDo("Listen Classical Music",true));
            stream.onNext(new ToDo("Workout in the Mornings"));
            stream.onNext(new ToDo("Organize my room", true));
            stream.onNext(new ToDo("Go to the Car Wash", true));
            stream.onNext(new ToDo("SP1 2018 is coming", true));

            stream.onComplete(); //Завершает предыдущую часть конвейера

            promise.block(); //Подписывается на тип Flux и осуществляет блокировку до момента получения следующего сигнала или истечения времени ожидания

        };
    }
}
