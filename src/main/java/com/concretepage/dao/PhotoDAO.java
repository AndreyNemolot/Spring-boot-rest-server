package com.concretepage.dao;

import com.concretepage.entity.Photo;
import com.concretepage.entity.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class PhotoDAO implements IPhotoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Photo> getAllPhotos() {
        String hql ="FROM Photo";
        return (List<Photo>) entityManager.createQuery(hql).getResultList();
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
        Photo ph = getPhotoById(photo.getPhoto_id());
        /*ph.setLogin(photo.getLogin());
        ph.setPassword(photo.getPassword());*/
        entityManager.flush();
    }

    @Override
    public void deletePhoto(int photoId) {
        entityManager.remove(getPhotoById(photoId));
    }

    @Override
    public boolean photoExists(String login, String password) {
        String hql = "FROM UserInfo as usr WHERE usr.login = ? or usr.password = ?";
        int count = entityManager.createQuery(hql).setParameter(1, login)
                .setParameter(2, password).getResultList().size();
        return count > 0;
    }

}
