package com.concretepage.dao;

import com.concretepage.entity.Album;
import com.concretepage.entity.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class AlbumDAO implements IAlbumDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> getAllAlbums() {
        String hql ="FROM Album";
        return (List<Album>) entityManager.createQuery(hql).getResultList();
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
    public void deleteAlbum(int albumId) {
        entityManager.remove(getAlbumById(albumId));
    }

    @Override
    public boolean albumExists(String title) {
        String hql = "FROM Album as alb WHERE alb.albumTitle = ?";
        int count = entityManager.createQuery(hql).setParameter(1, title)
                .getResultList().size();
        return count > 0;
    }
}
