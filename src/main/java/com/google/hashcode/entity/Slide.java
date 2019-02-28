package com.google.hashcode.entity;

import java.util.*;
import java.util.stream.Collectors;

public class Slide {

    List<Photo> photos = new ArrayList<>();
    Set<String> tags = new HashSet<>();

    public Slide() {
    }

    public Slide(List<Photo> photos, Set<String> tags) {
        this.photos = photos;
        this.tags = tags;
    }

    public static List<Slide> getSlidesWithVerticalPhotos(List<Photo> photos) {

        photos.sort(Comparator.comparing(photo -> photo.getTags().size()));

        if (photos.size() % 2 != 0) {
            photos.remove(photos.size() - 1);
        }

        List<Slide> slides = new ArrayList<>();

        for (int i = 0; i < photos.size() / 2; i++) {
            List<Photo> photoList = new ArrayList<>();

            photoList.add(photos.get(i));
            photoList.add(photos.get(photos.size() - (i + 1)));

            Slide slide = new Slide();

            slide.setPhotos(photoList);

            slide.setTags(photoList
                    .stream()
                    .map(t ->t.getTags())
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet()));

            slides.add(slide);
        }

        return slides;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
