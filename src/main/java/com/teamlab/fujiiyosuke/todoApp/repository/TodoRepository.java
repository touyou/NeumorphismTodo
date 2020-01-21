package com.teamlab.fujiiyosuke.todoApp.repository;

import com.teamlab.fujiiyosuke.todoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * TodoのRepositoryインターフェイス
 * @author fujii
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    /**
     * findAll order by createDate
     * @return createDateでソートされたデータ
     */
    @Query("select d from Todo d order by d.createDate desc")
    public List<Todo> findAllOrderByCreateDate();

    /**
     * findByName Query
     * @param name 検索する名前
     * @return 検索結果
     */
    @Query("select d from Todo d where d.name = :NAME")
    public List<Todo> findByName(@Param("NAME")String name);
}