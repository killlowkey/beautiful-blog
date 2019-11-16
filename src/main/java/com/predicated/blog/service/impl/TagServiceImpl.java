package com.predicated.blog.service.impl;

import com.predicated.blog.entity.Tag;
import com.predicated.blog.exception.CustomException;
import com.predicated.blog.exception.CustomExceptionEnum;
import com.predicated.blog.repository.TagRepository;
import com.predicated.blog.service.TagService;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository TagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag saveTag(Tag Tag) {
        Optional<Tag> optional = TagRepository.findByName(Tag.getName());
        if (optional.isPresent()) {
            return null;
        }
        return TagRepository.save(Tag);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag getTag(Long id) {
        return TagRepository.getOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return TagRepository.findAll(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag updateTag(Long id, Tag Tag) {
        Tag result = getTag(id);
        if (result == null) {
            throw  new CustomException(CustomExceptionEnum.NOT_FOUND_ENTITY);
        }

        BeanUtils.copyProperties(Tag, result);
        return TagRepository.save(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTag(Long id) {
        TagRepository.deleteById(id);
    }
}
