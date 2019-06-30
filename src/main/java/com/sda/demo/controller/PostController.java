package com.sda.demo.controller;

import com.sda.demo.entity.Post;
import com.sda.demo.model.UserDto;
import com.sda.demo.repository.PostRepository;
import com.sda.demo.service.PostService;
import com.sda.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postRepository;
    private PostController postController;
    private PostService postService;

    @Autowired
    @RequestMapping("/listOfPosts/")

    public String view(Model model) {
        List<Post> posts = this.postService.findAll();
        if (posts.isEmpty()) {
            return "redirect";
        } else {

            posts = postService.findAll();
            model.addAttribute("allPosts", posts);
            return "listOfPosts";

        }

    }
}
