package com.concretepage.dao;

import com.concretepage.controller.PhotoController;
import com.concretepage.entity.Album;
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
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class PhotoDAO implements IPhotoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Photo> getAllPhotos(int albumId) {
        String hql ="FROM Photo as ph WHERE ph.album.albumId = ?";
        return (List<Photo>) entityManager.createQuery(hql)
                .setParameter(1, albumId).getResultList();
    }

    @Override
    public Photo getPhotoById(int photoId) {
        return entityManager.find(Photo.class, photoId);
    }

    @Override
    public Photo addPhoto(Photo photo) {
        return entityManager.merge(photo);
    }

    @Override
    public void updatePhoto(Photo photo) {
        getPhotoById(photo.getPhotoId());
        entityManager.flush();
    }

    @Override
    public Photo deletePhoto(int photoId) {
        Photo photo = entityManager.find(Photo.class, photoId);
            entityManager.remove(photo);
            return photo;
    }



    @Override
    public boolean photoExists(Photo photo) {
        String hql = "FROM Photo as pht WHERE pht.photoLink = ? and pht.album.albumId = ?";
        int count = entityManager.createQuery(hql).setParameter(1, photo.getPhotoLink()).
                setParameter(2, photo.getAlbumId()).getResultList().size();
        return count > 0;
    }

    @Override
    public int getUserIDbyPhotoID(int photoId) {
        String hql = "FROM Photo pht INNER JOIN pht.album al WHERE pht.photoId = ?";
        Object[] obj = (Object[]) entityManager.createQuery(hql)
                .setParameter(1, photoId).getResultList().get(0);
        Album album = (Album)obj[1];
        return album.getUser().getId();
    }
}
