package com.lrj.myblogmybatis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.service.AdminTagService;
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
 * @date ：2020/2/21 21:18
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private AdminTagService adminTagService;
    //标签页
    @GetMapping("/tag")
    public String Tags(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Tag> list = adminTagService.getTags();
        System.out.println(list);
        PageInfo<Tag> pageInfo =new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    //新增
    @GetMapping("/tag/input")
    public String AddTag(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-add";
    }

    //新增或者修改时候表单提交的post 分两种

    /*新增*/
    @PostMapping("/tag/post")
    public String AddTag(@Valid Tag tag , BindingResult result, RedirectAttributes attributes){
        System.out.println("传进来的tag为"+tag.toString());
        /*
        这个接口有好几个rejectValue()函数， 它们是可以支持国际化的。
         */
        if(adminTagService.getByName(tag.getName())!=null){
            /*
            这个例子表示， 错误的字段(filed)是“name”， errorCode是“nameError”， 与资源文件对应， 第三个是defaultMessage。
             */
            result.rejectValue("name", "nameError","标签已经存在，不能添加重复的标签");
        }

        if (result.hasErrors()){
            return "admin/tags-add";
        }

        if(adminTagService.SaveTag(tag)>0){
            attributes.addFlashAttribute("message","新增成功");
        }else{
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/tag";
    }

    /*编辑修改*/
    @PostMapping("/tag/post/{id}")
    public String TagEdit(@PathVariable long id,@Valid Tag tag,BindingResult result,RedirectAttributes attributes){
        System.out.println("传进来的tag为"+tag.toString());
        if(adminTagService.getByName(tag.getName())!=null){
            result.rejectValue("name", "nameError","标签已经存在，你没有做任何修改");
        }
        if (result.hasErrors()){
            return "admin/tags-add";
        }
        if(adminTagService.updateTag(tag)>0){
            attributes.addFlashAttribute("message", "标签修改成功");
        }
        else {
            attributes.addFlashAttribute("message", "标签修改失败");
        }

        return "redirect:/admin/tag";
    }

    //编辑修改
    @GetMapping("/tag/{id}/input")
    public String Edit(@PathVariable Long id,Model model){
        Tag Oldtag = adminTagService.getById(id);
        model.addAttribute("tag", Oldtag);
        return "admin/tags-add";
    }

    //删除
    @GetMapping("/tag/{id}/delete")
    public String DeleteTag(@PathVariable long id){
        adminTagService.deleteById(id);
        return "redirect:/admin/tag";
    }
}
