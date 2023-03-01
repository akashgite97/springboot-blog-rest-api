package com.REST.blogapi.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.REST.blogapi.entities.Category;
import com.REST.blogapi.entities.Post;
import com.REST.blogapi.entities.User;
import com.REST.blogapi.exceptions.ResourceNotFoundException;
import com.REST.blogapi.payloads.PostDto;
import com.REST.blogapi.repositories.CategoryRepo;
import com.REST.blogapi.repositories.PostRepo;
import com.REST.blogapi.repositories.UserRepo;
import com.REST.blogapi.services.PostService;

@Service
public class PostServiceImpl implements PostService {
    
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setCreatedDate(new Date());
        post.setImageName("default");
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    public PostDto getPostById(Integer postId){

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));

        return this.modelMapper.map(post, PostDto.class);
    }

    public PostDto updatePost(PostDto postDto, Integer postId){

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        return this.modelMapper.map(post, PostDto.class);

    }

    public void deletePost(Integer postId){

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));

        this.postRepo.delete(post);
    }

     public List<PostDto> getAllPost(){

        List<Post> posts = this.postRepo.findAll();

        List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        
        return postDtos;
        
     }

     public List<PostDto> getPostsByUser(Integer userId){

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
        }

     public List<PostDto> getPostsByCategory(Integer categoryId){
        
         Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
    
         List<Post> posts = this.postRepo.findByCategory(category);

         List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

         return postDtos;
        }
}
