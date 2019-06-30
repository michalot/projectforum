package com.sda.demo.service;

import com.sda.demo.entity.Post;
import com.sda.demo.entity.User;
import com.sda.demo.exception.UnsupportedException;
import com.sda.demo.model.PostDto;
import com.sda.demo.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, UserService userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    public void savePost(PostDto postDto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        prepareCreateDate(postDto, sdf);
        Post postToSave = modelMapper.map(postDto, Post.class);
        User loggedUser = userService.getCurrentUser();
        postToSave.setUser(loggedUser);
        postRepository.save(postToSave);
    }

    private void prepareCreateDate(PostDto postDto, SimpleDateFormat sdf) {
        String newDateFormat = sdf.format(new Date());
        try {
            postDto.setCreateDate(sdf.parse(newDateFormat));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new UnsupportedException(e.getMessage());
        }
    }

    public List<PostDto> getAllPosts() {
        List<PostDto> dtoPosts = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        modelMapper.map(posts, dtoPosts);
        return dtoPosts;
    }

}



