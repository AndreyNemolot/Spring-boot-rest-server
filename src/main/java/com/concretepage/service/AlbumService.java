package com.concretepage.service;

import com.concretepage.controller.PhotoController;
import com.concretepage.dao.IAlbumDAO;
import com.concretepage.entity.Album;
import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements IAlbumService {

    @Autowired
    private IAlbumDAO albumDAO;

    @Override
    public List<Album> getAllAlbum(int id) {
        return albumDAO.getAllAlbums(id);
    }

    @Override
    public Album getAlbumById(int albumId) {
        return albumDAO.getAlbumById(albumId);
    }

    @Override
    public boolean addAlbum(Album album) {
        if (albumDAO.albumExists(album.getAlbumTitle())) {
            return false;
        } else {
            albumDAO.addAlbum(album);
            return true;
        }
    }

    @Override
    public void updateAlbum(Album album) {

    }

    @Override
    public List<Photo> deleteAlbum(int albumId) {
        return albumDAO.deleteAlbum(albumId);
    }
}
