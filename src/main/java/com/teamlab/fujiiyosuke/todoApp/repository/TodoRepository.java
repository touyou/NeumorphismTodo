package com.teamlab.fujiiyosuke.todoApp.repository;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}