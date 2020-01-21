package com.teamlab.fujiiyosuke.Todo.Repository;

import com.teamlab.fujiiyosuke.Todo.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}