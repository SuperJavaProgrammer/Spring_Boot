package com.apress.reactor.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}
}
/**
 1. Реактивные системы
 быстро реагирующие
 отказоустойчивые
 адаптивные - Система должна быстро реагировать при любой нагрузке
 ориентированы на обмен сообщениями

 Реактивные потоки данных (Reactive
 Streams, www.reactive-streams.org) — спецификация, определяющая четыре
 простых интерфейса (Publisher<T> — поставщик неограниченной последова-
 тельности элементов, публикующий их согласно потребностям подписчика;
 Subscriber<T> — подписчик, подписывается на издателя; Subscription отражает
 (один к одному) жизненный цикл подписанного на издателя подписчика;
 и Processor, представляющий этап обработки как подписчика, так и издателя

 2. Project Reactor
 Reactor предоставляет два реактивных,
 пригодных для компоновки асинхронных API; Flux [N] (для N элементов)
 и Mono [011] (для 0 или 1 элемента).
 Project Reactor предоставляет узлы-обработчики, операторы и таймеры,
 позволяющие поддерживать очень высокую пропускную способность в де-
 сятки миллионов сообщений в секунду при низком объеме потребляемой памяти.
 Мопо<Т> представляет собой специализированную разновидность интерфейса
 Publisher<T>, которая выдает один элемент, причем может (не обязательно)
 завершаться с помощью сигнала onComplete или onError
 Flux представляет собой разновидность Publishers>, являющуюся асинхрон
 ной последовательностью от 0 до N выдаваемых элементов, которая может
 (не обязательно) завершаться с помощью сигнала onComplete или onError
 */