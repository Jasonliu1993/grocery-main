package com.jwebcoder.grocerymain.photography.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Base64;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PhotographyControllerTest {

    @Autowired
    private PhotographyController photographyController;

    //    @Test
    public String getPhotoAsBase64() {
        try {
            File file = new File("/Users/Jason/Pictures/01头像/avatar01.jpg");

            byte[] bytes = Files.readAllBytes(file.toPath());
//            System.out.println(Base64.getEncoder().encodeToString(bytes));
            return Base64.getEncoder().encodeToString(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void uploadPhoto() {
        String photo = getPhotoAsBase64();
        System.out.println(photo);
        photographyController.uploadPhoto(photo);
    }
}