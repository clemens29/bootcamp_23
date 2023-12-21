package de.bootcamp.springTodoExample.web;

import java.util.List;

import de.bootcamp.springTodoExample.model.TodoCategory;
import de.bootcamp.springTodoExample.model.TodoListItem;

public class TodoListView {
    private String name;
    private Integer id;

    private List<TodoListItem> entries;

    private TodoCategory category;
    public TodoListView(String name, Integer id, List<TodoListItem> entries, TodoCategory category) {
        this.name = name;
        this.id = id;
        this.entries = entries;
        this.category = category;
    }

    public TodoCategory getCategory() {
        return category;
    }

    public void setCategory(TodoCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TodoListItem> getEntries() {
        return entries;
    }

    public void setEntries(List<TodoListItem> entries) {
        this.entries = entries;
    }
}
