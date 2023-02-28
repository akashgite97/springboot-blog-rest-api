package com.REST.blogapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.REST.blogapi.entities.Category;
import com.REST.blogapi.entities.Post;
import com.REST.blogapi.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
    
}
