package com.teamlab.fujiiyosuke.Todo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "deadline_at")
    @Temporal(TemporalType.DATE)
    private Date deadlineDate;

    @Column(name = "is_done")
    private Boolean done;

    public Todo(String name, Date deadlineDate) {
        this.name = name;
        this.createDate = new Date();
        this.deadlineDate = deadlineDate;
        this.done = false;
    }
}
