package com.tuxbe.demo_encrypt_data.todo;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class TodoService implements TodoDao{

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.getReferenceById(id);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public void insertTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        Todo todoById = this.getTodoById(todo.getId());
        if (todoById != null){
            todoRepository.save(todo);
        }
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todoById = this.getTodoById(id);
        if (todoById != null){
            todoRepository.delete(todoById);
        }
    }

    @Override
    public List<Todo> searchTodo(String keyword) {
        QTodo qTodo = QTodo.todo;
        BooleanBuilder builder = new BooleanBuilder();
        if (keyword != null){
            builder.or(qTodo.title.contains(keyword));
            builder.or(qTodo.description.contains(keyword));
        }
        Iterable<Todo> todos = todoRepository.findAll(builder);
        return StreamSupport.stream(todos.spliterator(), false).toList();
    }

    @Override
    public List<Todo> searchTodoComplete(boolean completed) {
        QTodo qTodo = QTodo.todo;
        BooleanBuilder builder = new BooleanBuilder();
        builder.or(qTodo.completed.eq(completed));
        return StreamSupport.stream(todoRepository.findAll(builder).spliterator(), false).toList();
    }
}
