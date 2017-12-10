package com.concretepage.controller;

import com.concretepage.entity.Album;
import com.concretepage.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private IAlbumService albumService;

    @GetMapping("albums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> list = albumService.getAllAlbum();
        return new ResponseEntity<List<Album>>(list, HttpStatus.OK);
    }

    @RequestMapping(value="album", method= RequestMethod.GET)
    public ResponseEntity<Album> getAlbumById(
            @RequestParam(value = "album_id") Integer id) {
        Album userInfo = albumService.getAlbumById(id);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("album")
    public ResponseEntity<Void> addAlbum(Album album, UriComponentsBuilder builder) {
        boolean flag = albumService.addAlbum(album);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/album/{user_id}").buildAndExpand(album.getAlbumId()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value="album")
    public ResponseEntity<Void> deleteAlbum(
            @RequestParam(value = "album_id") Integer id) {
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
