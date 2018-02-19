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
import java.util.List;

@Transactional
@Repository
public class AlbumDAO implements IAlbumDAO {

    @PersistenceContext
    private EntityManager entityManager;
    private static String UPLOADED_FOLDER = "D://temp//";

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> getAllAlbums(int id) {
        String hql ="FROM Album as alb WHERE alb.userId = ?";
        return (List<Album>) entityManager.createQuery(hql).setParameter(1, id)
                .getResultList();
    }

    @Override
    public Album getAlbumById(int albumId) {
        return entityManager.find(Album.class, albumId);
    }

    @Override
    public void addAlbum(Album album) {
        entityManager.merge(album);
    }

    @Override
    public void updateAlbum(Album album) {

    }

    @Override
    public List<Photo> deleteAlbum(int albumId) {
        String hql ="FROM Photo as ph WHERE ph.albumId = ?";
        List<Photo> photoList = (List<Photo>) entityManager.createQuery(hql)
                .setParameter(1, albumId).getResultList();
        deletePhoto(albumId);
        entityManager.remove(getAlbumById(albumId));

        return photoList;
    }

    private void deletePhoto(int albumId){
        String hql ="FROM Photo as ph WHERE ph.albumId = ?";
        List<Photo> photoList = (List<Photo>) entityManager.createQuery(hql)
                .setParameter(1, albumId).getResultList();
        if (photoList.size()>0){
            for (int i=0; i<photoList.size();i++) {

                Photo photo = entityManager.find(Photo.class, photoList.get(i).getPhotoId());
                if(deletePhoto(photo)) {
                    entityManager.remove(photo);
                }
            }
        }
    }
    private boolean deletePhoto(Photo photo){
        File file = new File(UPLOADED_FOLDER + photo.getPhotoLink());
        try {
            return file.delete();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean albumExists(String title) {
        String hql = "FROM Album as alb WHERE alb.albumTitle = ?";
        int count = entityManager.createQuery(hql).setParameter(1, title)
                .getResultList().size();
        return count > 0;
    }
}
