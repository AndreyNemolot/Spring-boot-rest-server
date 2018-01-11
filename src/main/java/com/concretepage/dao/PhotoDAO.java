package com.concretepage.dao;

import com.concretepage.controller.PhotoController;
import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.List;

@Transactional
@Repository
public class PhotoDAO implements IPhotoDAO {

    @PersistenceContext
    private EntityManager entityManager;
    private static String UPLOADED_FOLDER = "D://temp//";

    @Override
    public List<Photo> getAllPhotos(int albumId) {
        String hql ="FROM Photo as ph WHERE ph.albumId = ?";
        return (List<Photo>) entityManager.createQuery(hql)
                .setParameter(1, albumId).getResultList();
    }

    @Override
    public Photo getPhotoById(int photoId) {
        return entityManager.find(Photo.class, photoId);
    }

    @Override
    public void addPhoto(Photo photo) {
        entityManager.merge(photo);
    }

    @Override
    public void updatePhoto(Photo photo) {
        Photo ph = getPhotoById(photo.getPhotoId());
        entityManager.flush();
    }

    @Override
    public void deletePhoto(int photoId) {
        Photo photo = entityManager.find(Photo.class, photoId);
        if(delete(photo)) {
            entityManager.remove(getPhotoById(photoId));
        }
    }

    private boolean delete(Photo photo){
        File file = new File(UPLOADED_FOLDER + photo.getPhotoLink());
        try {
            return file.delete();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public boolean photoExists(String link) {
        String hql = "FROM Photo as pht WHERE pht.photoLink = ?";
        int count = entityManager.createQuery(hql).setParameter(1, link)
                .getResultList().size();
        return count > 0;
    }

}
