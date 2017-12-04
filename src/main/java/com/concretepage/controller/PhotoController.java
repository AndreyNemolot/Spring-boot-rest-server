package com.concretepage.controller;

import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;
import com.concretepage.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoController {

    @Autowired
    private IPhotoService photoService;
    private static String UPLOADED_FOLDER = "D://temp//";


    @RequestMapping(value="photo", method= RequestMethod.GET)
    public ResponseEntity<Photo> getPhotoById(
            @RequestParam(value = "photo_id") Integer id) {
        Photo photo = photoService.getPhotoById(id);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @GetMapping("photos")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> list = photoService.getAllPhotos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("photo")
    public ResponseEntity<Void> singleFileUpload(@RequestParam("file") MultipartFile file, int album_id) {

        String photoPath = UPLOADED_FOLDER + file.getOriginalFilename();
        boolean flag = photoService.addPhoto(new Photo(album_id, photoPath));
        if (!flag) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(photoPath);
            Files.write(path, bytes);



        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="photo")
    public ResponseEntity<Void> deletePhoto(
            @RequestParam(value = "photo_id") Integer id) {
        photoService.deletePhoto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
