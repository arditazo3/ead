package com.google.hashcode.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PhotoContainer {

    List<Photo> photos = new ArrayList<>();

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public PhotoContainer(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @param file input file
     * @return 2d array representing a pizza
     * @throws IOException parsing fail
     */
    public static List<Photo> parsePhotoLogic(String file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fileReader);
            //skip a line with slice instructions
            br.readLine();
            //declare cells array
            List<Photo> photos = new ArrayList<>();
            Long idPhoto = 0L;
            String fileLine;
            while ((fileLine = br.readLine()) != null) {

                String[] elementsPhoto = fileLine.split(" ");

                String positionString = elementsPhoto[0];
                Position position = null;
                if (positionString.equals(Position.HORIZONTAL.toString())) {
                    position = Position.HORIZONTAL;
                } else {
                    position = Position.VERTICAL;
                }

                Set<String> tags = new HashSet<>();
                String numberTagsString = elementsPhoto[1];
                Integer numberTagsParse = Integer.parseInt(numberTagsString);
                for (int i = 2; i < elementsPhoto.length; i++) {
                    String tag = elementsPhoto[i];

                    tags.add(tag);
                }

                Photo photo = new Photo(idPhoto, tags, position);

                photos.add(photo);

                idPhoto++;
            }
            return photos;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoContainer that = (PhotoContainer) o;
        return Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photos);
    }

    @Override
    public String toString() {
        return "PhotoContainer{" +
                "photos=" + photos +
                '}';
    }
}
