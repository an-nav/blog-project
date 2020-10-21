package com.anvna.blog.service;

import com.anvna.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @InterfaceName TypeService
 * @Description
 * @Author an_vna
 * @Date 2020/10/20 21:29
 * @Version V1.0
 **/
@Service
public interface TypeService {
    /**
     * 新增分类
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 查询 tupe
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 更新 type
     * @param id 主键 id
     * @param type
     * @return
     */
    Type updateType(Long id, Type type);

    /**
     * 删除 type
     * @param id
     */
    void deleteType(Long id);

    Type getTypeByName(String name);

    List<Type> listType();

}
