package com.apress.directory.repository;

import com.apress.directory.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person,String> {
    Person findByEmailIgnoreCase(@Param("email") String email); //Такой синтаксис указывает Spring Data REST на необходимость реализации этих методов и создания соответствующего SQL-оператора (на основе названия и полей из класса предметной области; в данном случае поля email).
}
