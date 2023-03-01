package com.REST.blogapi.services;

import java.util.List;

import com.REST.blogapi.payloads.PostDto;


public interface PostService {
    
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostsByCategory(Integer categoryId);
}
