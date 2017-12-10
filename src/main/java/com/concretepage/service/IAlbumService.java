package com.concretepage.service;

import com.concretepage.entity.Album;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IAlbumService{

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    List<Album> getAllAlbum();
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    Album getAlbumById(int albumId);
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    boolean addAlbum(Album album);
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    void updateAlbum(Album album);
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    void deleteAlbum(int albumId);
}
