package com.sda.demo.repository;

import com.sda.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {



}
