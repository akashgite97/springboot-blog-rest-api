package com.REST.blogapi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    
    public static final String UPLOAD_DIR = "C:\\Users\\akash\\OneDrive\\Desktop\\blog-rest-api\\blogapi\\src\\main\\resources\\static";

    public boolean uploadFile(MultipartFile multiPartFile){

        boolean f  =false;

        try {

           InputStream is =  multiPartFile.getInputStream();
           byte data[] = new byte[is.available()];

           is.read(data);
            
           //write data
            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+multiPartFile.getOriginalFilename());
            fos.write(data);
            fos.flush();
            fos.close();

       // Files.copy(multiPartFile.getInputStream(), UPLOAD_DIR+File.separator+multiPartFile.getOriginalFilename(),StandardCopyOption.REPLACE_EXISTING);

            f=true;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return f;
    }
}
