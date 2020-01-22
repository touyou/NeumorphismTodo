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
     * countByName Query
     * @param name 検索する名前
     * @return 該当数
     */
    @Query("select count(d) from Todo d where d.name = :NAME")
    public int countByName(@Param("NAME")String name);

    /**
     * countByNameNotId Query
     * @param name 検索する名前
     * @param id 除外するID
     * @return 該当数
     */
    @Query("select count(d) from Todo d where d.name = :NAME and d.id <> :ID")
    public int countByNameNotId(@Param("NAME")String name, @Param("ID")Long id);

    /**
     * findByPartOfName
     * @param name 検索語句
     * @return result
     */
    @Query("select d from Todo d where d.name like concat('%',:NAME,'%') escape '~' order by d.createDate desc")
    public List<Todo> findByPartOfName(@Param("NAME")String name);
}