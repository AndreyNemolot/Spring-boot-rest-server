package com.concretepage.controller;

import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;
import com.concretepage.service.IPhotoService;
import com.concretepage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoController {

    @Autowired
    private IPhotoService photoService;

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
    public ResponseEntity<Void> addPhoto(@RequestPart String title, @RequestPart MultipartFile img) throws IOException {

        /*boolean flag = photoService.addPhoto(photo);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }*/
        /*HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/photo/{photo_id}").buildAndExpand(photo.getPhoto_id()).toUri());*/
        File upl = new File("images/" + title);
        upl.createNewFile();
        FileOutputStream fout = new FileOutputStream(upl);
        fout.write(img.getBytes());
        fout.close();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value="photo")
    public ResponseEntity<Void> deletePhoto(
            @RequestParam(value = "photo_id") Integer id) {
        photoService.deletePhoto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
