package com.concretepage.dao;

import com.concretepage.controller.PhotoController;
import com.concretepage.entity.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Transactional
@Repository
public class AlbumDAO implements IAlbumDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getUserIDbyAlbumID(int albumId) {
        String hql = "FROM Album al WHERE al.albumId = ?";
        Album album = (Album) entityManager.createQuery(hql)
                .setParameter(1, albumId).getResultList().get(0);
        return album.getUser().getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> getAllAlbums(int id) {
        String hql ="FROM Album as alb WHERE alb.user.userId = ?";
        return (List<Album>) entityManager.createQuery(hql).setParameter(1, id)
                .getResultList();
    }

    @Override
    public Album getAlbumById(int albumId) {
        String hql ="FROM Album WHERE albumId = ?";

        return (Album) entityManager.createQuery(hql).setParameter(1, albumId)
                .getResultList().get(0);
    }

    @Override
    public void addAlbum(Album album) {
        entityManager.merge(album);
    }

    @Override
    public void updateAlbum(Album album) {
    }

    @Override
    public boolean deleteAlbum(int albumId) {
        Album album = getAlbumById(albumId);
        entityManager.remove(album);
        return true;
    }

    @Override
    public boolean albumExists(String title) {
        String hql = "FROM Album as alb WHERE alb.albumTitle = ?";
        int count = entityManager.createQuery(hql).setParameter(1, title)
                .getResultList().size();
        return count > 0;
    }
}
