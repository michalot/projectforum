package com.sda.demo.controller;

import com.sda.demo.entity.Post;
import com.sda.demo.model.PostDto;
import com.sda.demo.model.UserDto;
import com.sda.demo.repository.PostRepository;
import com.sda.demo.service.PostService;
import com.sda.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postRepository;
    private PostController postController;
    private PostService postService;


    @Autowired

    @GetMapping("/addpost")
    public ModelAndView addPost() {
        return new ModelAndView("post", "newPost",
                new PostDto());
    }

    @RequestMapping("/listOfPosts/")
    public String findAllPosts() {
        List<Post> posts = this.postService.findAll();
        if (posts.isEmpty()) {
            return "redirect";
        }  else {
            posts = postService.findAll();
            return "listOfPosts";

        }

    }
}
