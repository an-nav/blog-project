package com.anvna.blog.service.impl;

import com.anvna.blog.NotFoundException;
import com.anvna.blog.dao.TypeRepository;
import com.anvna.blog.po.Type;
import com.anvna.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName TypeServiceImpl
 * @Description
 * @Author an_vna
 * @Date 2020/10/20 21:32
 * @Version V1.0
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = getType(id);
        if( t == null ){
            throw new NotFoundException("不存在该类型");
        }

        BeanUtils.copyProperties(type, t);
        return saveType(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

}
