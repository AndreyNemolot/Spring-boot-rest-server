package com.concretepage.entity;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private int photoId;
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
    @Column(name = "photo_link")
    private String photoLink;

    public Photo(int albumId, String link) {
        this.album = new Album();
        this.album.setAlbumId(albumId);
        this.photoLink = link;
    }

    public Photo(){}

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getAlbumId() {
        return album.getAlbumId();
    }

    public void setAlbumId(int albumId) {
        this.album.setAlbumId(albumId);
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
