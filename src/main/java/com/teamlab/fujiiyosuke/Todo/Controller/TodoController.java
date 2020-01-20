package com.teamlab.fujiiyosuke.Todo.Controller;

import com.teamlab.fujiiyosuke.Todo.Entity.TodoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {

    @GetMapping("/")
    public String top(Model model) {
        model.addAttribute("list", TodoEntity.demoList);
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
