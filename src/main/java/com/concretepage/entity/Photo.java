package com.concretepage.entity;

import javax.persistence.*;

@Entity
@Table(name="photo")
public class Photo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="photo_id")
    private int photo_id;
    @Column(name="album_id")
    private int album_id;
    @Column(name="photo_link")
    private String photo_link;

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }
}
