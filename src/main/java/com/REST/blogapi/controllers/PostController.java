package com.REST.blogapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.REST.blogapi.payloads.ApiResponse;
import com.REST.blogapi.payloads.PostDto;
import com.REST.blogapi.payloads.PostResponse;
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

    @GetMapping("/get/{postId}")
    public ResponseEntity<PostDto> createUser(@PathVariable int postId) {

        PostDto post = this.postService.getPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<PostResponse> getAllUsers(
        @RequestParam(value = "pageNumber",defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = "10", required = false) Integer pageSize) {

        PostResponse allPosts = this.postService.getAllPost(pageNumber, pageSize);

        return new ResponseEntity<PostResponse>(allPosts, HttpStatus.CREATED);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable int postId) {

        PostDto post = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{postId}")
    public ApiResponse deletePost(@PathVariable int postId) {

        this.postService.deletePost(postId);

        return new ApiResponse("Post successfully deleted",true);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId) {

        List<PostDto> posts = this.postService.getPostsByUser(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {

        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
