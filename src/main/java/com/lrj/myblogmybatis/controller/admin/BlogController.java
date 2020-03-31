package com.lrj.myblogmybatis.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrj.myblogmybatis.dto.BlogQuery;
import com.lrj.myblogmybatis.dto.BlogSearch;
import com.lrj.myblogmybatis.dto.ShowBlog;
import com.lrj.myblogmybatis.pojo.Blog;
import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.pojo.Type;
import com.lrj.myblogmybatis.pojo.User;
import com.lrj.myblogmybatis.service.AdminBlogService;
import com.lrj.myblogmybatis.service.AdminTagService;
import com.lrj.myblogmybatis.service.AdminTypeService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/23 17:37
 */

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private AdminBlogService adminBlogService;
    @Autowired
    private AdminTypeService adminTypeService;
    @Autowired
    private AdminTagService adminTagService;


    @GetMapping("/blog")
    public String Blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<BlogQuery> blogs = adminBlogService.getAllBlogs();
        PageHelper.startPage(pageNum, 5);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        ListTypeAndTag(model);
        return "admin/blog";
    }

    //编辑修改链接
    @GetMapping("/blog/{id}/edit")
    public String Edit(@PathVariable long id ,Model model){
        //将之前发布的博客信息拉取下来
        ShowBlog oldBlog = adminBlogService.getBlogById(id);
        model.addAttribute("blog", oldBlog);
        this.ListTypeAndTag(model);
        return "admin/distributed-update";
    }

    //修改
    @PostMapping("/blog/update")
    public String BlogType(ShowBlog showBlog,RedirectAttributes attributes){
        if(adminBlogService.updateBlog(showBlog)>0){
            attributes.addFlashAttribute("message", "操作成功");
        }
        else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/blog";
    }

    //预览效果


    //辅助帮助查询类别与标签
    public void ListTypeAndTag(Model model){
        List<Tag> tags = adminTagService.getTags();
        List<Type> types = adminTypeService.getTypes();
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
    }


    //新增发布链接
    @GetMapping("/blog/input")
    public String AddBlog(Model model){
        ListTypeAndTag(model);
        return "admin/distributed";
    }

    //发布
    @PostMapping("/blog/add")
    public String Published(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(adminTypeService.getById(blog.getTypeId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        blog.setTags(adminTagService.getTagByString(blog.getTagIds()));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        adminBlogService.SaveBlog(blog);
        attributes.addFlashAttribute("message", "发布成功");
        return "redirect:/admin/blog";
    }

    //删除
    @GetMapping("/blog/{id}/delete")
    public String BlogDelete(@PathVariable long id, RedirectAttributes attributes){
        adminBlogService.DeleteById(id);
        attributes.addFlashAttribute("message", "操作成功");
        return "redirect:/admin/blog";
    }

    //博客搜索
    //根据标题和类别
    @PostMapping("/blog/search")
    public String BlogSearch(Model model,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
        BlogSearch BlogSearch){
        List<BlogQuery> list = adminBlogService.BlogSearch(BlogSearch);
        PageHelper.startPage(pageNum, 100);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        ListTypeAndTag(model);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询完成");
        model.addAttribute("BlogSearch", BlogSearch);
        return "admin/blog-search";
    }
}
