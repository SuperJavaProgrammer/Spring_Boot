package com.apress.todo.actuator;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="todo-stats") //Указывает, что данный тип является конечной точкой актуатора, выдающей информацию о запущенном приложении. Доступ к конечным точкам возможен посредством множества технологий, включая JMX и HTTP. В данном случае конечной точкой для актуатора является класс ToDoStatsEndpoint
public class ToDoStatsEndpoint { //пользовательская конечная точка, выполняющая такие операции, как отображение статистики (общего числа запланированных дел и количества завершенных дел), получение объекта ToDo, удаление его и перевод его в состояние завершенного

    private ToDoRepository toDoRepository;

    ToDoStatsEndpoint(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    @ReadOperation //Указывает, что метод конечной точки представляет собой операцию чтения
    public Stats stats() {
        return new Stats(this.toDoRepository.count(), this.toDoRepository.countByCompleted(true));
    }

    @ReadOperation
    public ToDo getToDo(@Selector String id) { //@Selector - аннотация для параметра метода конечной точки указывает, что этот параметр выбирает подмножество данных конечной точки
        return this.toDoRepository.findById(id).orElse(null);
    }

    @WriteOperation //Указывает, что метод конечной точки представляет собой операцию записи. Очень похоже на событие POST
    public Operation completeToDo(@Selector String id) {
        ToDo toDo = this.toDoRepository.findById(id).orElse(null);
        if(null != toDo) {
            toDo.setCompleted(true);
            this.toDoRepository.save(toDo);
            return new Operation("COMPLETED", true);
        }
        return new Operation("COMPLETED", false);
    }

    @DeleteOperation //Указывает, что метод конечной точки представляет собой операцию удаления
    public Operation removeToDo(@Selector String id) {
        try {
            this.toDoRepository.deleteById(id);
            return new Operation("DELETED", true);
        } catch(Exception ex) {
            return new Operation("DELETED", false);
        }
    }

    @Data
    public class Stats {
        private long count;
        private long completed;

        public Stats(long count, long completed) {
            this.count = count;
            this.completed = completed;
        }
    }

    @Data
    public class Operation{
        private String name;
        private boolean successful;

        public Operation(String name, boolean successful) {
            this.name = name;
            this.successful = successful;
        }
    }
}


