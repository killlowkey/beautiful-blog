package com.predicated.blog.controller.admin;

import com.predicated.blog.entity.Blog;
import com.predicated.blog.entity.User;
import com.predicated.blog.service.BlogService;
import com.predicated.blog.service.TagService;
import com.predicated.blog.service.TypeService;
import com.predicated.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpSession;

/**
 * @author Ray
 * @date created in 2019/11/16 10:42
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blogQuery, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        /*局部片段渲染*/
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {

        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());

        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog,
                       RedirectAttributes attributes,
                       HttpSession session) {

        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog result = blogService.saveBlog(blog);
        if (result == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String update(@PathVariable Long id, RedirectAttributes attributes) {
        Blog blog = blogService.getBlog(id);
        if (blog == null) {
            attributes.addFlashAttribute("message", "编辑失败，博客不存在");

        } else {
            attributes.addAttribute("blog", new Blog());
        }

        return "redirect:/admin/blogs/input";

    }
}
