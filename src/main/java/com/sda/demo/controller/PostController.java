package com.sda.demo.controller;

import com.sda.demo.model.PostDto;
import com.sda.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/addpost")
    public ModelAndView addPost() {
        return new ModelAndView
                ("post", "postToInsert", new PostDto());
    }

    @RequestMapping(value = "/addpost", method = RequestMethod.POST)
    public ModelAndView create(@Valid PostDto postDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (postDto.getText().isEmpty()) {
            bindingResult.rejectValue("text", "error.post", "Content cannot be empty");
        } else {
            postService.savePost(postDto);
            modelAndView.addObject("successMessage", "Post has been created");
            modelAndView.addObject("postToInsert", new PostDto());
        }
        modelAndView.setViewName("postAdded");
        return modelAndView;
    }


    @RequestMapping("/listOfPosts")
    public String findAllPosts(Model model) {
        List<PostDto> posts = this.postService.getAllPosts();
        model.addAttribute("allPosts", posts);
        return "listOfPosts";
    }
}
