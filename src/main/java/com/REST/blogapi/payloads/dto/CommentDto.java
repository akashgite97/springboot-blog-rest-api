package com.REST.blogapi.payloads.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    
    private int commentId;

    private String content;
}
