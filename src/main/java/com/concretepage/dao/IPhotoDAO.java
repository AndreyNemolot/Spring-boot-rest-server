package com.concretepage.dao;

import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;

import java.util.List;

public interface IPhotoDAO {
    List<Photo> getAllPhotos(int albumId);
    Photo getPhotoById(int photoId);
    void addPhoto(Photo photo);
    void updatePhoto(Photo photo);
    void deletePhoto(int photoId);
    boolean photoExists(String link);
}
