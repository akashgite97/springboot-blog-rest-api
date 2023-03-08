package com.REST.blogapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.REST.blogapi.constants.MessageConstants;
import com.REST.blogapi.payloads.dto.CommentDto;
import com.REST.blogapi.payloads.response.ApiResponse;
import com.REST.blogapi.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createCommnent(@RequestBody CommentDto comment, @PathVariable Integer postId) {

        CommentDto newComment = this.commentService.createComment(comment, postId);

        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{commentId}")
    public ApiResponse deleteuser(@PathVariable int commentId) {

        this.commentService.deleteComment(commentId);

        return new ApiResponse(MessageConstants.COMMENT_DELETE_SUCCESS, true);

    }

}
