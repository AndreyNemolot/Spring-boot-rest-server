package com.concretepage.dao;

import com.concretepage.entity.Album;

import java.util.List;

public interface IAlbumDAO {

        List<Album> getAllAlbums(int id);
        Album getAlbumById(int albumId);
        void addAlbum(Album album);
        void updateAlbum(Album album);
        void deleteAlbum(int albumId);
        boolean albumExists(String title);

}
