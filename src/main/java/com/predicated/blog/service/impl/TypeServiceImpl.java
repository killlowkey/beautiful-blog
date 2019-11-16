package com.predicated.blog.service.impl;

import com.predicated.blog.entity.Type;
import com.predicated.blog.exception.CustomException;
import com.predicated.blog.exception.CustomExceptionEnum;
import com.predicated.blog.repository.TypeRepository;
import com.predicated.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Ray
 * @date created in 2019/11/16 11:12
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type saveType(Type type) {
        Optional<Type> optional = typeRepository.findByName(type.getName());
        if (optional.isPresent()) {
            return null;
        }
        return typeRepository.save(type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Type updateType(Long id, Type type) {
        Type result = getType(id);
        if (result == null) {
            throw  new CustomException(CustomExceptionEnum.NOT_FOUND_ENTITY);
        }

        BeanUtils.copyProperties(type, result);
        return typeRepository.save(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
