package com.teamlab.fujiiyosuke.Todo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class TodoEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String createDate;
    private String limitDate;
    private Boolean isDone;

    private SimpleDateFormat formatter;

    public static List<TodoEntity> demoList = List.of(
            new TodoEntity("ToDoの表示", new Date()),
            new TodoEntity("ToDoの編集", new Date()),
            new TodoEntity("Todoの検索", new Date())
    );
    public static List<TodoEntity> emptyList = List.of();

    public TodoEntity(String name, Date limitDate) {
        this.name = name;
        this.formatter = new SimpleDateFormat("yyyy年MM月dd日");
        this.createDate = formatter.format(new Date());
        this.limitDate = formatter.format(limitDate);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                ", limitDate='" + limitDate + '\'' +
                ", isDone=" + isDone +
                '}';
    }

    // getter
    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public Boolean getDone() {
        return isDone;
    }
}
