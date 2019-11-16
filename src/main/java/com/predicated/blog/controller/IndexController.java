package com.predicated.blog.controller;

import com.predicated.blog.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ray
 * @date created in 2019/11/15 20:07
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    @Log("访问主页")
    public ModelAndView index() {
        System.out.println(1/0);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/blog")
    @Log("访问博客")
    public ModelAndView blog() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog");

        return mv;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags";
    }

    @GetMapping("/types")
    public String types() {
        return "types";
    }

    @GetMapping("/archives")
    public String archives() {
        return "archives";
    }

    @GetMapping("/admin/blogs")
    public String blogs() {
        return "/admin/blogs";
    }

    @GetMapping("/admin/blogs-input")
    public String input() {
        return "/admin/blogs-input";
    }
}

