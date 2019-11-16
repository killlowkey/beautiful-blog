package com.predicated.blog.controller.admin;

import com.predicated.blog.entity.Type;
import com.predicated.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




/**
 * @author Ray
 * @date created in 2019/11/16 11:19
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        Page<Type> typePage = typeService.listType(pageable);
        model.addAttribute("page", typePage);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String typeInput(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String inputById(@PathVariable Long id,
                            Model model) {
        Type type = typeService.getType(id);
        model.addAttribute("type", type);
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }


    @PostMapping("/types")
    public String save(Type type, RedirectAttributes attributes) {
        if (typeService.saveType(type) == null) {
            attributes.addFlashAttribute("message", "操作失败, 该分类已经存在");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String saveById(@PathVariable Long id, Type type, RedirectAttributes attributes) {
        Type result = typeService.getType(id);
        if (result == null) {
            attributes.addFlashAttribute("message", "操作失败, id不存在");
        }

        typeService.updateType(id, type);
        attributes.addFlashAttribute("message", "操作成功");

        return "redirect:/admin/types";
    }

}
