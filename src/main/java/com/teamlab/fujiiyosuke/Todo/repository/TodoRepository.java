package com.teamlab.fujiiyosuke.Todo.repository;

import com.teamlab.fujiiyosuke.Todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}