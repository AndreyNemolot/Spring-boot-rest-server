package com.concretepage.service;

import com.concretepage.dao.IPhotoDAO;
import com.concretepage.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PhotoService implements IPhotoService {

    @Autowired
    private IPhotoDAO photoDAO;

    private final String UPLOADED_FOLDER = "D://temp//";

    public String getPhotoPathOnServer(Photo photo) {
        return UPLOADED_FOLDER + getUserIDbyPhotoID(photo.getPhotoId()) +
                "//" + photo.getAlbumId();
    }

    @Override
    public List<Photo> getAllPhotos(int albumId) {
        return photoDAO.getAllPhotos(albumId);
    }

    @Override
    public Photo getPhotoById(int photoId) {
        Photo obj = photoDAO.getPhotoById(photoId);
        return obj;
    }

    @Override
    public Photo addPhoto(Photo photo) {
        if (photoDAO.photoExists(photo)) {
            return null;
        } else {
            return photoDAO.addPhoto(photo);
        }
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoDAO.updatePhoto(photo);
    }

    @Override
    public Photo deletePhoto(int photoId) {
        deletePhotoFile(getPhotoById(photoId));
        return photoDAO.deletePhoto(photoId);
    }

    private boolean deletePhotoFile(Photo photo) {
        File file = new File(getPhotoPathOnServer(photo) + "//" + photo.getPhotoLink());
        try {
            return file.delete();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public int getUserIDbyPhotoID(int photoId) {
        return photoDAO.getUserIDbyPhotoID(photoId);
    }
}
