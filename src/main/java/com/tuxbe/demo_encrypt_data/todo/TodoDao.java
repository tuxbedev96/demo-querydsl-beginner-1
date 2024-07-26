package com.tuxbe.demo_encrypt_data.todo;

import java.util.List;

public interface TodoDao {
    Todo getTodoById(Long id);
    List<Todo> getAllTodos();
    void insertTodo(Todo todo);
    void updateTodo(Todo todo);
    void deleteTodo(Long id);
    List<Todo> searchTodo(String keyword);
    List<Todo> searchTodoComplete(boolean completed);
}
