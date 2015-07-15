package com.jarninfang.spotifystreamer;

/**
 * Created by jarnin on 7/9/15.
 */
public class TrackInfo {
    private String imageUrl;
    private String songName;
    private String albumName;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
