package com.REST.blogapi.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.REST.blogapi.constants.AppConstants;
import com.REST.blogapi.constants.MessageConstants;
import com.REST.blogapi.payloads.ApiResponse;
import com.REST.blogapi.payloads.PostDto;
import com.REST.blogapi.payloads.PostResponse;
import com.REST.blogapi.services.FileService;
import com.REST.blogapi.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createCategory(@Valid @RequestBody PostDto postDto, @PathVariable int userId,
            @PathVariable int categoryId) {

        PostDto post = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<PostDto> createUser(@PathVariable int postId) {

        PostDto post = this.postService.getPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<PostResponse> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

        PostResponse allPosts = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

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

        return new ApiResponse(MessageConstants.POST_DELETE_SUCCESS, true);
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

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keywords) {

        List<PostDto> searchResults = this.postService.searchPost("%" + keywords + "%");

        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("file") MultipartFile file,
            @PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadFile(path, file);

        postDto.setImageName(fileName);

        PostDto updatedPost = this.postService.updatePost(postDto, postId);

        // if(file.isEmpty()){
        // return new ResponseEntity<>(new FileResponse(f, "Reuqest must contain file",
        // false));
        // }

        // if(!file.getContentType().equals("image/jpeg")){
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only
        // jpeg content type are allowed");
        // }

        // if(f!=""){
        // return ResponseEntity.ok("File uploaded successfully");
        // }

        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
