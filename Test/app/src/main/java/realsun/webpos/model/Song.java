package realsun.webpos.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;


public class Song extends DataSupport {

    @Column(nullable = false)
    private String name;

    private int duration;

    @Column(ignore = true)
    private String uselessField;

    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setName(String name) {
        this.name = name;
    }

}
