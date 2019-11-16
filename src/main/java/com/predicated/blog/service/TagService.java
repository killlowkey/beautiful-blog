package com.predicated.blog.service;

import com.predicated.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author Ray
 * @date created in 2019/11/16 11:10
 */
public interface TagService {

    Tag saveTag(Tag Tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id, Tag Tag);

    void deleteTag(Long id);
}
