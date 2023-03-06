package com.REST.blogapi.payloads.response;

import java.util.List;

import com.REST.blogapi.payloads.dto.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLastPage;



}
