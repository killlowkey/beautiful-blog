package com.predicated.blog.controller;

import com.predicated.blog.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ray
 * @date created in 2019/11/15 20:07
 */
@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    @Log("访问主页")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    @ResponseBody
    @Log("测试")
    public String hello(@RequestParam(value = "name", defaultValue = "hello") String name,
                        @RequestParam(value = "age", defaultValue = "10") Integer age) {
        return name;
    }
}
