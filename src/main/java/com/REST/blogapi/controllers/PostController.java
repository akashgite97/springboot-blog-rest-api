package com.REST.blogapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.REST.blogapi.payloads.PostDto;
import com.REST.blogapi.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;
    
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createCategory(@Valid @RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId) {

        PostDto post = this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
