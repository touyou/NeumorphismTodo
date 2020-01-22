package com.teamlab.fujiiyosuke.todoApp.controller;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import com.teamlab.fujiiyosuke.todoApp.form.TodoForm;
import com.teamlab.fujiiyosuke.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * TodoのControllerクラス
 * @author fujii
 */
@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    /**
     * Index page
     * @param form 空のフォームデータもしくはエラー時の元の入力値が入る
     * @param mav ModelAndView
     * @return 設定済のModelAndView
     */
    @GetMapping("/")
    public ModelAndView index(@ModelAttribute("todoForm")TodoForm form, ModelAndView mav) {
        mav.setViewName("top");
        mav.addObject("formatter", new SimpleDateFormat("yyyy年MM月dd日"));
        mav.addObject("list", todoService.findAllOrderByCreateDate());
        return mav;
    }

    /**
     * Add request
     * @param form バリデーションを含めたフォームデータ
     * @param result バリデーション結果
     * @param mav ModelAndView
     * @return 加工済のModelAndView
     */
    @PostMapping("/add")
    @Transactional(readOnly = false)
    public ModelAndView add(@ModelAttribute("todoForm")@Validated TodoForm form, BindingResult result, ModelAndView mav) {
        if (todoService.countByName(form.getName()) > 0) {
            result.rejectValue("name", "error.name", "同名のTodoが存在しています。");
        }
        if (result.hasErrors()) {
            return index(form, mav);
        }
        Todo newTodo = new Todo(form.getName(), form.getDeadline());
        todoService.create(newTodo);
        return new ModelAndView("redirect:/");
    }

    /**
     * Doneの切り替え
     * @param id 切り替えるTodoのID
     * @param mav ModelAndView
     * @return Indexへのリダイレクト
     */
    @PostMapping("/done/{id}")
    @Transactional(readOnly = false)
    public ModelAndView switchStatus(@PathVariable Long id, ModelAndView mav) {
        Optional<Todo> optionalTodo = todoService.findById(id);
        optionalTodo.ifPresent(todo -> {
            todo.setDone(!todo.getDone());
            todoService.update(todo);
        });
        return new ModelAndView("redirect:/");
    }

    /**
     * Edit View
     * @param id path id
     * @param form 空のフォーム
     * @param mav ModelAndView
     * @return 設定済みのModelAndView
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, @ModelAttribute("todoForm")TodoForm form, ModelAndView mav) {
        mav.setViewName("edit");
        Optional<Todo> optionalTodo = todoService.findById(id);
        optionalTodo.ifPresent(todo -> {
            form.setName(todo.getName());
            form.setDeadline(todo.getDeadlineDate());
        });
        mav.addObject("id", id);
        return mav;
    }

    /**
     * Update request
     * @param id path id
     * @param form フォームのデータ
     * @param result バリデーションの結果
     * @param mav ModelAndView
     * @return 設定済みのModelAndView
     */
    @PostMapping("/edit/{id}")
    @Transactional(readOnly = false)
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("todoFom")@Validated TodoForm form, BindingResult result, ModelAndView mav) {
        if (todoService.countByNameNotId(form.getName(), id) > 0) {
            result.rejectValue("name", "error.name", "同名のTodoが存在しています。");
        }
        if (result.hasErrors()) {
            mav.setViewName("edit");
            mav.addObject("todoForm", form);
            mav.addObject("id", id);
            mav.addObject("validationError", result.getFieldErrors());
            return mav;
        }
        Todo todo = todoService.findById(id).get();
        todo.setName(form.getName());
        todo.setDeadlineDate(form.getDeadline());
        todoService.update(todo);
        return new ModelAndView("redirect:/");
    }

    /**
     * Search page
     * @param model リクエストパラメータ
     * @return viewのパス
     */
    @GetMapping("/search")
    public String search(Model model) {
        return "search";
    }
}
