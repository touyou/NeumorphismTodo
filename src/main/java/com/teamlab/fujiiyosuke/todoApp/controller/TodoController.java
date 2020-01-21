package com.teamlab.fujiiyosuke.todoApp.controller;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import com.teamlab.fujiiyosuke.todoApp.form.TodoForm;
import com.teamlab.fujiiyosuke.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("todoForm")TodoForm form, ModelAndView mav) {
        mav.setViewName("top");
        mav.addObject("formatter", new SimpleDateFormat("yyyy年MM月dd日"));
        mav.addObject("list", todoService.findAll());
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("todoForm")@Validated TodoForm form, BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            return index(form, mav);
        }
        Todo newTodo = new Todo(form.getName(), form.getDeadline());
        todoService.create(newTodo);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        return "edit";
    }

    @GetMapping("/search")
    public String search(Model model) {
        return "search";
    }
}
