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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;
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
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
     * Edit View
     * @param form
     * @param id
     * @param mav
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute("todoForm")TodoForm form, @PathVariable Long id, ModelAndView mav) {
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
     * @param form
     * @param result
     * @param id
     * @param mav
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView update(@ModelAttribute("todoFom")@Validated TodoForm form, BindingResult result, @PathVariable("id")Long id, ModelAndView mav) {
        List<Todo> todos = todoService.findByNameNotId(form.getName(), id);
        if (!todos.isEmpty()) {
            result.rejectValue("name", "error.name", "同名のTodoが存在しています。");
        }
        if (result.hasErrors()) {
            return edit(form, id, mav);
        }
        System.out.println(result);
        Todo todo = todoService.findById(id).get();
        todo.setName(form.getName());
        todo.setDeadlineDate(form.getDeadline());
        todoService.update(todo);
        System.out.println(todo);
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
