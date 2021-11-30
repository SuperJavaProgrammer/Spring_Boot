package com.apress.todo.controller;

import com.apress.todo.domain.ToDo;
import com.apress.todo.domain.ToDoBuilder;
import com.apress.todo.repository.CommonRepository;
import com.apress.todo.validation.ToDoValidationError;
import com.apress.todo.validation.ToDoValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController //объявлять отображение запросов, входные данные запросов, обработку ошибок и др
@RequestMapping("/api") //задает отображение запросов на методы контроллера, у всех методов будет /api префикс
public class ToDoController {
    private CommonRepository<ToDo> repository;

    @Autowired //внедряет реализацию CommonRepository<To Do> можно опустить; Spring автоматически внедряет все объявленные зависимости, начиная с версии 4.3
    public ToDoController(CommonRepository<ToDo> repository) {
        this.repository = repository;
    }

    @GetMapping("/todo") //@RequestMapping(value="/todo", method = {RequestMethod.GET})
    public ResponseEntity<Iterable<ToDo>> getToDos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id){
        return ResponseEntity.ok(repository.findById(id));
    }

    @PatchMapping("/todo/{id}") //сокращенное написание  @RequestMapping; помечает запланированное дело как выполненное
    public ResponseEntity<ToDo> setCompleted(@PathVariable String id){ //связывание с /todo/{id}
        ToDo result = repository.findById(id);
        result.setCompleted(true);
        repository.save(result);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location", location.toString()).build();
    }

    @RequestMapping(value="/todo", method = {RequestMethod.POST, RequestMethod.PUT}) //один метод на обработку POST и PUT
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo, Errors errors){ //ResponseEntity - возвращает полный ответ, включая HTTP-заголовки //Valid - проверяет корректность поступающих данных и используется в параметрах метода //RequestBody - отправляет запрос, включающий тело
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/todo/{id}") //для для удаления запланированного дела
    public ResponseEntity<ToDo> deleteToDo(@PathVariable String id){
        repository.delete(ToDoBuilder.create().withId(id).build());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
        repository.delete(toDo);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler //Spring MVС автоматически объявляет встроенные средства разрешения для исключений и добавляет поддержку в этой аннотации
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //отправляет обратно заданный в ответе код состояния HTTP
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }

}
