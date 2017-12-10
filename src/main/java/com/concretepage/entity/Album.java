package com.concretepage.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="album_id")
    private int albumId;
    @Column(name="user_id")
    private int userId;
    @Column(name="album_title")
    private String albumTitle;

    public Album() {}

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public int getAlbumId() {

        return albumId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }
}
