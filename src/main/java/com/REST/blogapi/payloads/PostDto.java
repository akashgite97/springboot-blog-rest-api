package com.REST.blogapi.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    
    private int postId;

    private String title;

     private String content;

     private String imageName;

    private Date createdDate;

    private CategoryDto category;

    private UserDto user;
    
}
