package de.bootcamp.springTodoExample.repo;

import de.bootcamp.springTodoExample.model.TodoList;
import de.bootcamp.springTodoExample.model.TodoListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
