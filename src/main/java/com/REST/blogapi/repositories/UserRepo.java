package com.REST.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.REST.blogapi.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


}