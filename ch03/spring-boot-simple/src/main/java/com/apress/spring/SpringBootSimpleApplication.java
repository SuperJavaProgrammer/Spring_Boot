package com.apress.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**1 Автоматическая конфигурация
 1.1 Отключение конкретных автоконфигурационных классов.
 @RestController
 @EnableAutoConfiguration(exclude=[ActiveMQAutoConfiguration.class])
 @SpringBootApplication(exclude={ActiveMQAutoConfiguration.class, DataSourceAutoConfiguration.class})
 1.2 Аннотации @EnableAutoConfiguration и @Enable<технология>
 @EnableTransactionManagement, @EnableRabbit и @EnableIntegration
 Можно следовать паттерну «соглашения важнее конфигурации».
 Автоконфигурация использует путь к классам, чтобы определить, какие настройки необходимы для приложения.
 */
@SpringBootApplication
public class SpringBootSimpleApplication {
//public class SpringBootSimpleApplication implements CommandLineRunner, ApplicationRunner {
	private static final Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);

		/**2 Возможности Spring Boot
		@SpringBootApplication. Фактически эта аннотация представляет собой аннотации @ComponentScan, @Configuration и @EnableAutoConfiguration.
		SpringApplication. Этот класс предоставляет первоначальный загрузчик приложения Spring Boot, выполняемый в методе main.
		Необходимо только передать выполняемый класс.
		2.1 Класс SpringApplication
		Класс SpringApplication дает возможность более продвинутой настройки путем создания его экземпляра и выполнения намного более расширенных действий.
		*/
//		public static void main(String[] args) {
//			SpringApplication.run(SpringBootSimpleApplication.class, args);
//			SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
//			//add more features here
//			app.run(args);
//		}

		/**2.2 Пользовательский баннер
		Заданный в коде текстовый баннер будет перекрыт файлом banner.txt, расположенным по пути к классам (classpath).
		$ ./mvnw spring-boot:run -Dspring.banner.location=classpath:/META-INF/banner.txt - задать другое место.
		Это свойство можно объявить в файле src/main/resources/application.properties:
		spring.banner.location=classpath:/МЕТА-INF/banner.txt
		spring.main.banner-mode=off - убрать
		 */
//		public static void main(String[] args) throws IOException {
//			SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
//			app.setBanner(new Banner() {
//				@Override
//				public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//					out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
//				}
//			});
//			app.run(args);
//
//			SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
//			app.setBannerMode(Banner.Mode.OFF); //убрать программно
//			app.run(args);
//		}

		/**2.3 Класс SpringApplicationBuilder
		Предоставляет текучий API и представляет собой построитель экземпляров SpringApplication и Applicationcontext.
		Можно также использовать следующие дополнительные события:
		 ApplicationStartedEvent (отправляется при запуске приложения),
		 ApplicationEnvironmentPreparedEvent (отправляется, когда становится известной среда),
		 ApplicationPreparedEvent (отправляется после определений компонентов),
		 ApplicationReadyEvent (отправляется, когда приложение готово к работе),
		 ApplicationFailedEvent (отправляется в случае возникновения исключений во время запуска приложения).
		 */
//		public static void main(String[] args) throws IOException {
//			new SpringApplicationBuilder()
//					.bannerMode(Banner.Mode.OFF)
//					.sources(SpringBootSimpleApplication.class)
//					.child(MyConfig.class)
//					.logStartupInfo(false)
//					.profiles("production")
//					.web(WebApplicationType.NONE)
//					.listeners(new ApplicationListener<ApplicationEvent>() {
//						@Override
//						public void onApplicationEvent(ApplicationEvent event) {
//							log.info("#### > " + event.getClass().getCanonicalName());
//						}
//					})
//					.run(args);
//		}

		/**2.4 Аргументы приложения
			Передача параметров в исполняемый JAR:
			$ ./mvnw package - для создания JAR
			$ java -jar spring-boot-simple-0.0.1-SNAPSHOT.jar - запустить
			$ java -jar spring-boot-simple-0.0.1-SNAPSHOT.jar --enable arg1 arg2 - передавать аргументы
		 */
//		public static void main(String[] args) {
//			SpringApplication.run(SpringBootSimpleApplication.class, args);
//		}
//		@Component
//		class MyComponent {
//			private final Logger log = LoggerFactory.getLogger(MyComponent.class);
//			@Autowired
//			public MyComponent(ApplicationArguments args) {
//				//Step. Get if enable argument was passed in the form:  --enable or --enable=true
//				boolean enable = args.containsOption("enable");
//				log.info("### > You are " + (enable ? "enable" : "disable"));
//				//Step. Get argument files in the form:  myfile.txt  or files=["myfile.txt"]  or  enable=true, files=["myfile.txt"]
//				log.info("### > extra args...");
//				List<String> extra = args.getNonOptionArgs();
//				if(!extra.isEmpty())
//					extra.forEach(file -> log.info(file));
//			}
//		}

	/**3 Интерфейсы ApplicationRunner и CommandLineRunner
	 Spring Boot после выполнения приложения SpringApplication завершает работу.
	 Использовать компоненты после завершения выполнения данного класса нельзя, но существует решение этой проблемы.
	 */
//		public static void main(String[] args) {
//			SpringApplication.run(SpringBootSimpleApplication.class, args);
//		}
//		@Bean
//		String info(){
//			return "Just a simple String bean";
//		}
//		@Autowired
//		String info;
//		@Override
//		public void run(ApplicationArguments args) throws Exception {
//			log.info("ApplicationRunner Implementation... Accessing the Info bean: " + info);
//			args.getNonOptionArgs().forEach(file -> log.info(file));
//		}
//		@Override
//		public void run(String... args) throws Exception { //не надо 2 реализовывать, они одинаковые
//			log.info("CommandLineRunner Implementation...Accessing the Info bean: " + info);
//			for(String arg:args)
//				log.info(arg);
//		}
//		@Bean //вместо public void run(String... args)
//		CommandLineRunner myMethod(){
//			return args -> {
//				log.info("CommandLineRunner Implementation with @Bean...Accessing the Info bean: " + info);
//				for(String arg:args)
//					log.info(arg);
//			};
//		}

	/**3.1 Конфигурация приложения
	 В случае Spring можно использовать либо XML и тег <context:propertyplaceholder/ >,
	 либо аннотацию @PropertySource, указывающую на файл, где объявлены свойства Spring Boot - файл application.properties,
	 который должен располагаться в корневом каталоге пути к классам приложения.
	 воспользоваться файлом application.yml в формате YAML
	 задействовать переменные среды (для облачных сценариев)
	 задействовать аргументы командной строки
	 data.server=remoteserver:3030
	 @Value("${data.server}”)
	 private String server;
	 $ java -jar target/myapp.jar --data.server=remoteserver:3030 - через КС
	 $ SPRING_APPLICATION_JSON='{"data":{"server": "remoteserver: 3030"}} ’ java -jar target/myapp.jar - переменная среды

	 3.2 Примеры использования свойств конфигурации
	 Аргументы командной строки;
	 SPRING_APPLICATION_JSON;
	 JNDI (java:comp/env);
	 System.getProperties();
	 переменные среды операционной системы;
	 класс RandomValuePropertySource (random.*);
	 свойства, относящиеся к конкретному профилю (аррИса!1оп-{профиль} .jar) вне JAR-файла пакета;
	 свойства, относящиеся к конкретному профилю (application-{профиль}. jar) внутри JAR-файла пакета;
	 свойства приложения (файл application.properties) вне JAR-файла пакета;
	 свойства приложения (файл application.properties) внутри JAR-файла пакета;
	 аннотация @PropertySource;
	 заданные с помощью метода SpringApplication. setDefaultProperties.

	 Изменение местоположения и названия
	 Spring Boot ищет файлы application.properties и YAML в определенном порядке:
	 1. В подкаталоге /config текущего каталога.
	 2. В текущем каталоге.
	 3. В пакете /config, расположенном по пути к классам.
	 4. В корневом каталоге пути к классам.
	 Ролный список стандартных свойств приложений:
	 https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
	 -Dspring.config.name=mycfg - задать новое имя конф. файла. Включать в название .properties не обязательно; это расширение добавляется автоматически
	 -Dspring.config.location=file:app/ - задать новое размещение

	 Свойства, относящиеся к конкретному профилю
	 @ActiveProfiles
	 или получить текущий объект Environment и воспользоваться методом setActiveProfiles
	 spring.profiles.active
	 указать файл свойств в следующем формате: application-{профиль}.properties.
	 Если не задать никаких активных профилей, будет использоваться профиль по умолчанию, то есть файл application.properties
	 3.3 Пользовательский префикс для свойств
	 Необходимо только снабдить аннотацией @ConfigurationProperties Java-класс с сеттерами и геттерами для свойств.
	 @ConfigurationProperties(prefix="myapp") - класс используется для всех описанных в файле application.properties аннотаций с префиксом myapp
	 */
//	public static void main(String[] args) {
//		SpringApplication.run(SpringBootSimpleApplication.class, args);
//	}
//	@Value("${myapp.server-ip}")
//	String serverIp;
//	@Value("${server.ip}")
//	String serverIp2;
//	@Autowired
//	MyAppProperties props;
//	@Bean
//	CommandLineRunner values(){
//		return args -> {
//			log.info(" > The Server IP is: " + serverIp);
//			log.info(" > The Server IP2 is: " + serverIp2);
//			log.info(" > App Name: " + props.getName());
//			log.info(" > App Info: " + props.getDescription());
//		};
//	}
//	@Component
//	@ConfigurationProperties(prefix="myapp")
//	public static class MyAppProperties {
//		private String name;
//		private String description;
//		private String serverIp;
//
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getDescription() {
//			return description;
//		}
//		public void setDescription(String description) {
//			this.description = description;
//		}
//		public String getServerIp() {
//			return serverIp;
//		}
//		public void setServerIp(String serverIp) {
//			this.serverIp = serverIp;
//		}
//	}

}
