package com.concretepage.dao;

import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;

import java.util.List;

public interface IPhotoDAO {
    List<Photo> getAllPhotos(int albumId);

    Photo getPhotoById(int photoId);

    Photo addPhoto(Photo photo);

    void updatePhoto(Photo photo);

    Photo deletePhoto(int photoId);

    boolean photoExists(Photo photo);

    int getUserIDbyPhotoID(int photoId);
}
