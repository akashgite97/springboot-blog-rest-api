package com.REST.blogapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.REST.blogapi.entities.User;
import com.REST.blogapi.exceptions.ResourceNotFoundException;
import com.REST.blogapi.repositories.UserRepo;

public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //load user from database by username
        User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email"+username, 0));
        
        return null;
    }
    
    

}
