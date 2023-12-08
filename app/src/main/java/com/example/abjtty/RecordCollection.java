package com.example.abjtty;

public class RecordCollection {
    public RecordCollection(String artist, String title, String format, int value) {
        this.artist = artist;
        this.title = title;
        this.format = format;
        this.value = value;
    }

    private int id;
    private String artist;
    private String title;
    private String format;
    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
