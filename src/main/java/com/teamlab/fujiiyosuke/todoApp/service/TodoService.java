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
     * count data by name
     * @param name Todo名
     * @return Todo名が同じデータの数
     */
    public int countByName(String name) {
        return todoRepository.countByName(name);
    }

    /**
     * 名前が一緒かつIDの異なるものの検索
     * @param name Todo名
     * @param id ID
     * @return Todo名が同じで違うデータ
     */
    public List<Todo> findByNameNotId(String name, Long id) {
        return todoRepository.findByNameNotId(name, id);
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
     * update todo
     * @param todo 更新されたTodoインスタンス
     * @return 保存結果のTodo(disposable)
     */
    public Todo update(Todo todo) {
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
