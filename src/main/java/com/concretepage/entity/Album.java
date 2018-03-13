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
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo user;
    @Column(name="album_title")
    private String albumTitle;

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Album() {
        this.user = new UserInfo();
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public UserInfo getUser(){
        return user;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }
}
