package com.lrj.myblogmybatis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrj.myblogmybatis.pojo.Type;
import com.lrj.myblogmybatis.service.AdminTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/22 21:54
 */

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private AdminTypeService adminTypeService;

    @GetMapping("/type")
    public String Type(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Type> list = adminTypeService.getTypes();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }
    /*新增*/
    @GetMapping("/type/input")
    public String AddType(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-add";
    }

    //添加
    @PostMapping("/post")
    public String TypeAdd(@Valid Type type,BindingResult result, RedirectAttributes attributes){
        if(adminTypeService.getByName(type.getName())!=null){
            result.rejectValue("name", "nameError", "类别已经存在，不能添加重复的类别");
        }
        if(result.hasErrors()){
            return "admin/types-add";
        }

        if(adminTypeService.SaveType(type)>0){
            attributes.addFlashAttribute("message", "新增类别成功");
        }else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/type";
    }

    /*编辑修改*/
    @GetMapping("/type/{id}/input")
    public String Edit(@PathVariable long id, Model model){
        model.addAttribute("type", adminTypeService.getById(id));
        return "admin/types-add";
    }
    //修改更新
    @PostMapping("/post/{id}")
    public String TypeEdit(@Valid Type type, BindingResult result,@PathVariable long id, RedirectAttributes attributes){
        if(adminTypeService.getByName(type.getName())!=null){
            result.rejectValue("name", "nameError", "类别已经存在了，你没有做任何修改");
        }
        if (result.hasErrors()){
            return "admin/types-add";
        }

        if(adminTypeService.updateType(type)>0){
            attributes.addFlashAttribute("message", "类别更新成功");
        }else {
            attributes.addFlashAttribute("message", "操作失败");
        }

        return "redirect:/admin/type";
    }
    @GetMapping("/type/{id}/delete")
    public String TypeDelete(@PathVariable long id){
        adminTypeService.deleteById(id);
        return "redirect:/admin/type";
    }

}
