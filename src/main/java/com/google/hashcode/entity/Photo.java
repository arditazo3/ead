package com.google.hashcode.entity;

import java.util.*;

public class Photo {

    Long idPhoto = null;
    Set<String> tags = new HashSet<>();
    Position position;

    public Photo() {
    }

    public Photo(Long idPhoto, Set<String> tags, Position position) {
        this.idPhoto = idPhoto;
        this.tags = tags;
        this.position = position;
    }

    public Long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(idPhoto, photo.idPhoto) &&
                Objects.equals(tags, photo.tags) &&
                position == photo.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhoto, tags, position);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "idPhoto=" + idPhoto +
                ", tags=" + tags +
                ", position=" + position +
                '}';
    }
}
