package com.teamlab.fujiiyosuke.Todo.Controller;

import com.teamlab.fujiiyosuke.Todo.Entity.TodoEntity;
import com.teamlab.fujiiyosuke.Todo.Repository.TodoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    private TodoEntityRepository repository;

    @GetMapping("/")
    public String top(Model model) {
        model.addAttribute("formatter", new SimpleDateFormat("yyyy年MM月dd日"));
        model.addAttribute("list", repository.findAll());
        return "top";
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
