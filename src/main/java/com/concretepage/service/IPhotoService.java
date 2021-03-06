package com.concretepage.service;

import com.concretepage.entity.Photo;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IPhotoService {
        String getPhotoPathOnServer(Photo photo);
        @Secured({"ROLE_ADMIN", "ROLE_USER"})
        List<Photo> getAllPhotos(int albumId);
        @Secured({"ROLE_ADMIN", "ROLE_USER"})
        Photo getPhotoById(int photoId);
        @Secured({"ROLE_ADMIN", "ROLE_USER"})
        Photo addPhoto(Photo photo);
        @Secured({"ROLE_ADMIN", "ROLE_USER"})
        void updatePhoto(Photo photo);
        @Secured({"ROLE_ADMIN", "ROLE_USER"})
        Photo deletePhoto(int photoId);
        @Secured({"ROLE_ADMIN", "ROLE_USER"})
        int getUserIDbyPhotoID(int photoId);
    }