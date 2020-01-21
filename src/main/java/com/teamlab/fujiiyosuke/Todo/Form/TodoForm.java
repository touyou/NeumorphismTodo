package com.teamlab.fujiiyosuke.Todo.Form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TodoForm {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    // getter/setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
