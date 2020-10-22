package com.anvna.blog.dao;

import com.anvna.blog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @InterfaceName TypeRepository
 * @Description
 * @Author an_vna
 * @Date 2020/10/20 21:33
 * @Version V1.0
 **/
public interface TypeRepository extends JpaRepository<Type, Long> {
    /**
     * 通过分类名查询分类
     */
    Type findByName(String name);

    /**
     * 查询前 n 个 tag
     * @param pageable
     * @return
     */
    @Query("select t from t_type t")
    List<Type> findTop(Pageable pageable);

}
