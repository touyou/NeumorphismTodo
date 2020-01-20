package com.teamlab.fujiiyosuke.Todo.Repository;

import com.teamlab.fujiiyosuke.Todo.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoEntityRepository extends JpaRepository<TodoEntity, Long> {
}