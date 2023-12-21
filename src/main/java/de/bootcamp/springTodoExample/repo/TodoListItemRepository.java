package de.bootcamp.springTodoExample.repo;

import de.bootcamp.springTodoExample.model.TodoListItem;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListItemRepository extends JpaRepository<TodoListItem, Integer> {
    List<TodoListItem> findAllByTodoListId(Integer todoListId);

    void deleteAllByTodoListId(Integer todoListId);
}
