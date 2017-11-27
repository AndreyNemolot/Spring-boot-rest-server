package com.concretepage.service;

import com.concretepage.dao.IPhotoDAO;
import com.concretepage.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService implements IPhotoService {

    @Autowired
    private IPhotoDAO photoDAO;

    @Override
    public List<Photo> getAllPhotos() {
        return photoDAO.getAllPhotos();
    }

    @Override
    public Photo getPhotoById(int photoId) {
        Photo obj = photoDAO.getPhotoById(photoId);
        return obj;
    }

    @Override
    public boolean addPhoto(Photo photo) {
        /*if (photoDAO.photoExists(photo.getLogin(), photo.getPassword())) {
            return false;
        } else {*/
            photoDAO.addPhoto(photo);
            return true;
       // }
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoDAO.updatePhoto(photo);
    }

    @Override
    public void deletePhoto(int photoId) {
        photoDAO.deletePhoto(photoId);
    }
}