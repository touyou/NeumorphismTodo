package com.teamlab.fujiiyosuke.todoApp.service;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import com.teamlab.fujiiyosuke.todoApp.exception.TodoNotFoundException;
import com.teamlab.fujiiyosuke.todoApp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Date;
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
     * find all ordered by created_at
     * @return all list
     */
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
     * find data by part of name
     * @param name 検索語句
     * @return 検索結果
     */
    public List<Todo> findByPartOfName(String name) {
        if (name == null) {
            return new ArrayList<Todo>();
        }
        return todoRepository.findByPartOfName(sqlEscape(name));
    }

    /**
     * Add validation
     * @param result BindingResult
     * @param name add name
     */
    public void addValidate(BindingResult result, String name) {
        if (todoRepository.countByName(HtmlUtils.htmlEscape(name)) > 0) {
            result.rejectValue("name", "error.name", "同名のTodoが存在しています。");
        }
    }

    /**
     * Edit validation
     * @param result BindingResult
     * @param name edit name
     * @param id current id
     */
    public void editValidate(BindingResult result, String name, Long id) {
        if (todoRepository.countByNameNotId(HtmlUtils.htmlEscape(name), id) > 0) {
            result.rejectValue("name", "error.name", "同名のTodoが存在しています。");
        }
    }

    /**
     * create new data
     * @param name name
     * @param date deadline date
     * @return 保存結果のTodo(disposable)
     */
    public Todo create(String name, Date date) {
        Todo todo = new Todo(HtmlUtils.htmlEscape(name), date);
        return todoRepository.save(todo);
    }

    /**
     * update todo
     * @param id ID
     * @param newName new name
     * @param newDate new date
     * @return 保存結果のTodo(disposable)
     * @throws TodoNotFoundException not found exception
     */
    public Todo update(Long id, String newName, Date newDate) {
        Optional<Todo> opTodo = findById(id);
        if (opTodo.isPresent()) {
            Todo todo = opTodo.get();
            todo.setName(HtmlUtils.htmlEscape(newName));
            todo.setDeadlineDate(newDate);
            return todoRepository.save(todo);
        }
        throw new TodoNotFoundException();
    }

    /**
     * Doneの状態の切り替え
     * @param id ID
     * @return 保存結果のTodo(disposable)
     * @throws TodoNotFoundException not found exception
     */
    public Todo updateDone(Long id) {
        Optional<Todo> opTodo = findById(id);
        if (opTodo.isPresent()) {
            Todo todo = opTodo.get();
            todo.setDone(!todo.getDone());
            return todoRepository.save(todo);
        }
        throw new TodoNotFoundException();
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

    /**
     * SQL escaping
     * @param word original word
     * @return result
     */
    private String sqlEscape(String word) {
        return HtmlUtils.htmlEscape(word)
                .replace("%", "~%")
                .replace("_", "~_");
    }
}
