package com.apress.todo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity //Класс является сущностью и сохраняется в выбранной СУБД
@Data
//@NoArgsConstructor //В JPA должен быть конструктор без аргументов
public class ToDo {

    @Id //Задает первичный ключ сущности. Снабженное этой аннотацией поле должно относиться к простому типу данных Java или адаптеру для простого типа данных
    @GeneratedValue(generator = "system-uuid") //Определяет стратегии генерации значений первичного ключа (только для простых, несоставных ключей). Обычно оно используется вместе с аннотацией @ld
    @GenericGenerator(name = "system-uuid", strategy = "uuid") //составная часть Hibernate позволяет использовать стратегию для генерации уникального ID из предыдущей аннотации
    private String id;
    @NotNull
    @NotBlank
    private String description;

    @Column(insertable = true, updatable = false) //Задает отображаемый в сохраняемое свойство столбец; если для поля отсутствует аннотация @Column, в качестве значения по умолчанию используется название столбца таблицы базы данных
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;

    ToDo(String description){
        this.description = description;
    }

    @PrePersist //представляет собой обратный вызов, запускаемый до всех действий, связанных с сохранением. Задает новую метку даты/времени для созданных и модифицированных полей до вставки записи в базу данных
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate //представляет собой обратный вызов, запускаемый до всех действий, связанных с обновлением данных. Задает новую метку даты/времени для модифицированного поля до его обновления в базе данных
    //@PrePersist и @Prellpdate представляют собой очень удобный способ работы с метками даты/времени, упрощая работу разработчика
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }

}
