package com.REST.blogapi.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    long fieldValue;
   
    public ResourceNotFoundException( String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found this %s; %l", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
  
  

    
}
