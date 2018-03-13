package com.concretepage.dao;

import com.concretepage.entity.Album;
import com.concretepage.entity.Photo;

import java.util.List;

public interface IAlbumDAO {

    List<Album> getAllAlbums(int id);

    Album getAlbumById(int albumId);

    void addAlbum(Album album);

    void updateAlbum(Album album);

    boolean deleteAlbum(int albumId);

    boolean albumExists(String title);

    int getUserIDbyAlbumID(int albumId);

}
