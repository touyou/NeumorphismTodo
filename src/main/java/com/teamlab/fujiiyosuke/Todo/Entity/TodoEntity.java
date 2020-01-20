package com.teamlab.fujiiyosuke.Todo.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todo")
public class TodoEntity {

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
    private Boolean isDone;

    public TodoEntity(String name, Date deadlineDate) {
        this.name = name;
        this.createDate = new Date();
        this.deadlineDate = deadlineDate;
        this.isDone = false;
    }

    private TodoEntity() {}

    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                ", limitDate='" + deadlineDate + '\'' +
                ", isDone=" + isDone +
                '}';
    }

    // getter/setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Boolean getDone() {
        return isDone;
    }
    public void setDone(Boolean done) {
        isDone = done;
    }
}
