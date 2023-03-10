package com.REST.blogapi.services;

import java.util.List;

import com.REST.blogapi.payloads.dto.PostDto;
import com.REST.blogapi.payloads.response.PostResponse;


public interface PostService {
    
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> searchPost(String keyword);
}
