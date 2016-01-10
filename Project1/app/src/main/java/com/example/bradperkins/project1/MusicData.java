package com.example.bradperkins.project1;

/**
 * Created by bradperkins on 1/8/16.
 */
public class MusicData{

    public String title;
    public String artist;
    public int  songUri;
    public int cover;
    

    public MusicData(String _artist, String _title, int _songUri, int _cover){
        artist = _artist;
        title = _title;
        songUri = _songUri;
        cover = _cover;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getSongUri() {
        return songUri;
    }

    public int getCover() {
        return cover;
    }
}
