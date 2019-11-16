package com.predicated.blog.service.impl;

import com.predicated.blog.entity.Tag;
import com.predicated.blog.exception.CustomException;
import com.predicated.blog.exception.CustomExceptionEnum;
import com.predicated.blog.repository.TagRepository;
import com.predicated.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Ray
 * @date created in 2019/11/16 11:12
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag saveTag(Tag Tag) {
        Optional<Tag> optional = tagRepository.findByName(Tag.getName());
        if (optional.isPresent()) {
            return null;
        }
        return tagRepository.save(Tag);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    /**
     * 根据tag id 查找 Tag
     * @param ids
     * @return
     */
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if ("".equals(ids) && ids == null) {
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(new Long(split[i]));
            }
        }

        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag updateTag(Long id, Tag Tag) {
        Tag result = getTag(id);
        if (result == null) {
            throw  new CustomException(CustomExceptionEnum.NOT_FOUND_ENTITY);
        }

        BeanUtils.copyProperties(Tag, result);
        return tagRepository.save(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }


}
