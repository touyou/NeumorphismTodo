package com.teamlab.fujiiyosuke.Todo.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Todo {
    private String name;
    private String createDate;
    private String limitDate;
    private Boolean isDone;

    private SimpleDateFormat formatter;

    public static List<Todo> demoList = List.of(
            new Todo("ToDoの表示", new Date()),
            new Todo("ToDoの編集", new Date()),
            new Todo("Todoの検索", new Date())
    );
    public static List<Todo> emptyList = List.of();

    public Todo(String name, Date limitDate) {
        this.name = name;
        this.formatter = new SimpleDateFormat("yyyy年MM月dd日");
        this.createDate = formatter.format(new Date());
        this.limitDate = formatter.format(limitDate);
        this.isDone = false;
    }

    // getter
    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Date getCreateDateAsDate() throws ParseException {
        return formatter.parse(createDate);
    }

    public String getLimitDate() {
        return limitDate;
    }

    public Date getLimitDateAsDate() throws ParseException {
        return formatter.parse(limitDate);
    }

    public Boolean getDone() {
        return isDone;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = formatter.format(createDate);
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = formatter.format(limitDate);
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
