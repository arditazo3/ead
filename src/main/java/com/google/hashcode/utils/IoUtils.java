package com.google.hashcode.utils;

import com.google.hashcode.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Ex Armundia Devs
 */
public class IoUtils {
    private IoUtils() {
    }

    /**
     * Parses given input file to a 2d pizza cells array
     *
     * @param file input file
     * @return 2d array representing a pizza
     * @throws IOException parsing fail
     */
    public static List<Photo> parseObjectLogic(String file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fileReader);
            //skip a line with slice instructions
            br.readLine();
            //declare cells array
            List<Photo> photos = new ArrayList<>();
            Long idPhoto = 1L;
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
                    for (int i = 2; i < numberTagsParse; i++) {
                        String tag = elementsPhoto[i];

                        tags.add(tag);
                    }

                    Photo photo = new Photo(idPhoto, tags, position);

                idPhoto++;
            }
            return photos;
        }
    }


    /**
     * Formats data from list of slices to the required output format
     *
     * @param list inner representation of pizza
     * @return String that contains output data
     */
    public static String parseContainSlides(List<Slide> list) {

        StringBuilder sb = new StringBuilder();
        Formatter textFormatter = new Formatter(sb);
        textFormatter.format("%d%n", list.size());
        for (Slide slide : list) {

            if (slide.getPhotos().size() == 2) {
                textFormatter.format("%d %d%n", slide.getPhotos().get(0).getIdPhoto(), slide.getPhotos().get(1).getIdPhoto());
            } else {
                textFormatter.format("%d%n", slide.getPhotos().get(0).getIdPhoto());
            }
        }
        textFormatter.close();
        return sb.toString().trim();
    }

    public static void writeToFile(String fileName, String outputDate) throws IOException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(outputDate);
        }
    }

    public static String readFromFile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        lines.forEach(
                line -> stringBuilder.append(line).append("\n")
        );
        return stringBuilder.toString();
    }
}

