package com.REST.blogapi.services;

import java.util.List;

import com.REST.blogapi.payloads.PostDto;
import com.REST.blogapi.payloads.PostResponse;


public interface PostService {
    
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostsByCategory(Integer categoryId);
}
