package com.REST.blogapi.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.REST.blogapi.entities.Comment;
import com.REST.blogapi.entities.Post;
import com.REST.blogapi.exceptions.ResourceNotFoundException;
import com.REST.blogapi.payloads.dto.CommentDto;
import com.REST.blogapi.repositories.CommentRepo;
import com.REST.blogapi.repositories.PostRepo;
import com.REST.blogapi.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        
        System.out.println("postId"+postId);

        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        System.out.println("incoming comment"+comment);
        System.out.println("before comment set"+post);
        comment.setPost(post);
        System.out.println("after cokmment set"+post);

        Comment newComment = this.commentRepo.save(comment);

        return this.modelMapper.map(newComment, CommentDto.class);

        
    }

    @Override
    public void deleteComment(Integer commentId) {
        
        Comment comment  = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));

        this.commentRepo.delete(comment);
    }
    
    
}
