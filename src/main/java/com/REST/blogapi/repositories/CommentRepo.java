package com.REST.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.REST.blogapi.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    
}
