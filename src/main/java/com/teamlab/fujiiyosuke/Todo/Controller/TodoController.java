package com.teamlab.fujiiyosuke.Todo.Controller;

import com.teamlab.fujiiyosuke.Todo.Repository.TodoRepository;
import com.teamlab.fujiiyosuke.Todo.Service.TodoService;
import com.teamlab.fujiiyosuke.Todo.Entity.TodoEntity;
import com.teamlab.fujiiyosuke.Todo.Form.TodoForm;
import com.teamlab.fujiiyosuke.Todo.Repository.TodoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String top(Model model) {
        model.addAttribute("formatter", new SimpleDateFormat("yyyy年MM月dd日"));
        model.addAttribute("list", todoService.findAll());
        return "top";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute("todoForm")TodoForm form, Model model, RedirectAttributes redirectAttributes) {
        // todo: add validation
        TodoEntity newTodo = new TodoEntity(form.getName(), form.getDeadline());
        repository.save(newTodo);
        return "redirect:/";
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
