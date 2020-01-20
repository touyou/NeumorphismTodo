package com.teamlab.fujiiyosuke.Todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditController {

    @GetMapping("/edit")
    public String edit(Model model) {
        return "edit";
    }
}
