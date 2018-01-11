package com.concretepage.controller;

import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;
import com.concretepage.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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


    @RequestMapping(value = "photo", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> getPhotoById(
            @RequestParam(value = "photo_id") Integer id) {
        Photo photo = photoService.getPhotoById(id); // TODO: 24.12.2017 Если фото уже есть то ок
        File file = new File(UPLOADED_FOLDER + photo.getPhotoLink());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping("photos")
    public ResponseEntity<List<Photo>> getAllPhotos(
        @RequestParam(value = "album_id") int albumId) {
        List<Photo> list = photoService.getAllPhotos(albumId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("photo")
    public ResponseEntity<Void> singleFileUpload(@RequestParam("file") MultipartFile file, int album_id) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        String photoPath = file.getOriginalFilename();
        Photo ph = new Photo(album_id, photoPath);
        boolean flag = photoService.addPhoto(ph);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + photoPath);
                Files.write(path, bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping(value = "photo")
    public ResponseEntity<Void> deletePhoto(
            @RequestParam(value = "photo_id") Integer id) {
        photoService.deletePhoto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
