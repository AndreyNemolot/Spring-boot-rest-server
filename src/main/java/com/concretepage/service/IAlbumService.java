package com.concretepage.service;

import com.concretepage.entity.Album;
import com.concretepage.entity.Photo;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IAlbumService{

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    List<Album> getAllAlbum(int id);
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    Album getAlbumById(int albumId);
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    boolean addAlbum(Album album);
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    void updateAlbum(Album album);
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    boolean deleteAlbum(int albumId);
    int getUserIDbyAlbumID(int albumId);
    String getAlbumPathOnServer(int albumId);
}
