package com.teamlab.fujiiyosuke.Todo.Entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Column(name = "limit_at")
    @Temporal(TemporalType.DATE)
    private Date limitDate;

    @Column(name = "is_done")
    private Boolean isDone;

    public TodoEntity(String name, Date limitDate) {
        this.name = name;
        this.createDate = new Date();
        this.limitDate = limitDate;
        this.isDone = false;
    }

    private TodoEntity() {}

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

    public Date getLimitDate() {
        return limitDate;
    }
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Boolean getDone() {
        return isDone;
    }
    public void setDone(Boolean done) {
        isDone = done;
    }
}
