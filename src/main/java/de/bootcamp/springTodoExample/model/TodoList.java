package de.bootcamp.springTodoExample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TodoList {
    private String name;
    @Id
    @GeneratedValue
    private Integer id;
    private TodoCategory category;

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

}
