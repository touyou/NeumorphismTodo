package com.teamlab.fujiiyosuke.todoApp.repository;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select d from Todo d where d.name = :NAME")
    public List<Todo> findByName(@Param("NAME")String name);
}