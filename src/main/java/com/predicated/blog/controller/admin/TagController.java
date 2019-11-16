package com.predicated.blog.controller.admin;

import com.predicated.blog.entity.Tag;
import com.predicated.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author Ray
 * @date created in 2019/11/16 11:19
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        Page<Tag> tagPage = tagService.listTag(pageable);
        model.addAttribute("page", tagPage);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String tagInput(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String inputById(@PathVariable Long id,
                            Model model) {
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag", tag);
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags")
    public String save(Tag tag, RedirectAttributes attributes) {
        if (tagService.saveTag(tag) == null) {
            attributes.addFlashAttribute("message", "操作失败, 该标签已经存在");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String saveById(@PathVariable Long id, Tag tag, RedirectAttributes attributes) {
        Tag result = tagService.getTag(id);
        if (result == null) {
            attributes.addFlashAttribute("message", "操作失败, id不存在");
        }

        tagService.updateTag(id, tag);
        attributes.addFlashAttribute("message", "操作成功");

        return "redirect:/admin/tags";
    }

}
