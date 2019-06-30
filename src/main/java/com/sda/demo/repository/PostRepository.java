package com.sda.demo.repository;

import com.sda.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostByText(String text);
 //   @Query(value="select p.* from post p order by p.date desc", nativeQuery = true)
 //   List<Post> findAllOPosts(Pageable pageable);
}
