package com.apress.todo.repository;

import com.apress.todo.domain.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, String> {
    long countByCompleted(boolean completed); //метод для именованного запроса и создание соответствующего оператора SQL для подсчета числа завершенных запланированных дел производит модуль Spring Data
}
