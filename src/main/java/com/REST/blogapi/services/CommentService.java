package com.REST.blogapi.services;

import com.REST.blogapi.payloads.dto.CommentDto;

public interface CommentService {
    
    public CommentDto createComment(CommentDto commentDto, Integer postId);

    public void deleteComment(Integer commentId);
}
