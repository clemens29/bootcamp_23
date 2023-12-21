package de.bootcamp.springTodoExample.web;

import de.bootcamp.springTodoExample.model.TodoCategory;
import de.bootcamp.springTodoExample.model.TodoList;
import de.bootcamp.springTodoExample.model.TodoListItem;
import de.bootcamp.springTodoExample.repo.TodoListRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import de.bootcamp.springTodoExample.repo.TodoListItemRepository;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
    private final TodoListRepository todoListRepository;
    private final TodoListItemRepository todoListItemRepository;

    public TodoListController(TodoListRepository todoListRepository, TodoListItemRepository todoListItemRepository) {
        this.todoListRepository = todoListRepository;
        this.todoListItemRepository = todoListItemRepository;
    }

    @GetMapping
    public List<TodoListView> get() {
        var entries = todoListRepository.findAll();
        var result = new LinkedList<TodoListView>();
        for (var entry : entries) {
            var view = new TodoListView(entry.getName(), entry.getId(), entries(entry.getId()), entry.getCategory());
            result.add(view);
        }
        return result;
    }

    @PostMapping
    public TodoList insert(@RequestBody String name, @RequestParam("category") TodoCategory category){
        var newTodoList = new TodoList();
        newTodoList.setName(name);
        newTodoList.setCategory(category);
        return todoListRepository.save(newTodoList);
    }

    @GetMapping("/{todoListId}/")
    public List<TodoListItem> entries(@PathVariable("todoListId") Integer todoListId) {
        return todoListItemRepository.findAllByTodoListId(todoListId);
    }

    @PostMapping("/{todoListId}/")
    public TodoListItem addEntry(@PathVariable("todoListId") Integer todoListId, @RequestBody String title) {
        var entry = new TodoListItem();
        entry.setTodoListId(todoListId);
        entry.setTitle(title);
        entry.setDone(false);
        return todoListItemRepository.save(entry);
    }

    @PutMapping("/setDone/{todoListEntryId}/")
    public Optional<TodoListItem> setDone(@PathVariable("todoListEntryId") Integer todoListEntryId) {
        var result = todoListItemRepository.findById(todoListEntryId);
        if (result.isPresent()) {
            var item = result.get();
            item.setDone(true);
            return Optional.of(todoListItemRepository.save(item));
        } else {
            return Optional.empty();
        }
    }

    @PutMapping("/setDeadline/{todoListEntryId}/")
    public Optional<TodoListItem> setDeadline(@PathVariable("todoListEntryId") Integer todoListEntryId, @RequestBody String dateString) throws ParseException {
        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var date = LocalDate.parse(dateString, format);
        var result = todoListItemRepository.findById(todoListEntryId);
        if (result.isPresent()) {
            var item = result.get();
            item.setDeadline(date);
            return Optional.of(todoListItemRepository.save(item));
        } else {
            return Optional.empty();
        }
    }

    @PostMapping("/generateTestData")
    public void generateTestData() throws ParseException {
        var einkauf = insert("Einkauf", TodoCategory.HOBBY);
        var work = insert("Work", TodoCategory.WORK);
        addEntry(einkauf.getId(), "Huehnchen");
        addEntry(einkauf.getId(), "Reis");
        setDeadline(einkauf.getId(), "01.01.2021");
        addEntry(work.getId(), "Meeting");
        addEntry(work.getId(), "Report");
    }

    @GetMapping("/deadlineEnding/{deadline}/")
    public List<TodoListItem> deadlineEnding(@PathVariable("deadline") String dueDate ){
        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        var date = LocalDate.parse(dueDate,format);
        var allTodoListItems = todoListItemRepository.findAll();
        var allTodoListItemsEnding = new LinkedList <TodoListItem>();
        for(var entry : allTodoListItems){
            if(Objects.equals(entry.getDeadline(), date)){
                allTodoListItemsEnding.add(entry);
            }
        }
        return allTodoListItemsEnding;
    }

    @Transactional
    @DeleteMapping("/deleteTodoList/{todoListId}")
    public void deleteTodoList(@PathVariable("todoListId") Integer todoListId) {
        todoListItemRepository.deleteAllByTodoListId(todoListId);
        todoListRepository.deleteById(todoListId);
    }
}
