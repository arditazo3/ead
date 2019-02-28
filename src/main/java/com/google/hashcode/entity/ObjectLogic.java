package com.google.hashcode.entity;

import java.io.File;
import java.util.*;

/**
 * Represents an immutable pizza
 *
 * @author Ex Armundia Devs
 */
public class ObjectLogic {

    private final File input;
    private Set<Photo> photos;

    public ObjectLogic(File input, Set<Photo> photos) {
        this.input = input;
        this.photos = photos;
    }

    public File getInput() {
        return input;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectLogic that = (ObjectLogic) o;
        return Objects.equals(input, that.input) &&
                Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, photos);
    }

    @Override
    public String toString() {
        return "ObjectLogic{" +
                "input=" + input +
                ", photos=" + photos +
                '}';
    }
}
