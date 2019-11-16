package com.predicated.blog.service;

import com.predicated.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author Ray
 * @date created in 2019/11/16 11:10
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
