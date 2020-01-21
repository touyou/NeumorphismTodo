package com.teamlab.fujiiyosuke.todoApp.service;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import com.teamlab.fujiiyosuke.todoApp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Todoのサービスクラス
 * @author fujii
 */
@Service
@Transactional
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    /**
     * find all data
     * @return all data in database
     */
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public List<Todo> findAllOrderByCreateDate() {
        return todoRepository.findAllOrderByCreateDate();
    }

    /**
     * find data by id
     * @param id Todoのid
     * @return idと一致したデータ
     */
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    /**
     * find data by name
     * @param name Todo名
     * @return Todo名が同じデータ
     */
    public Optional<Todo> findByName(String name) {
        List<Todo> todos = todoRepository.findByName(name);
        return todos.stream().findFirst();
    }

    /**
     * create new data
     * @param todo 新しいTodoインスタンス
     * @return 保存結果のTodo(disposable)
     */
    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    /**
     * delete data by id
     * @param id 削除したいTodoのID
     */
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    /**
     * delete all data
     */
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
