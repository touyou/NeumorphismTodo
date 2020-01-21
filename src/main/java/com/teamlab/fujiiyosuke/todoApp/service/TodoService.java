package com.teamlab.fujiiyosuke.todoApp.service;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import com.teamlab.fujiiyosuke.todoApp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    public Optional<Todo> findByName(String name) {
        List<Todo> todos = findAll();
        return todos.stream().filter(t -> t.getName().equals(name)).findFirst();
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
