package com.google.hashcode;

import com.google.hashcode.entity.*;
import com.google.hashcode.utils.IoUtils;
import com.google.hashcode.utils.Profiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.hashcode.entity.PhotoContainer.parsePhotoLogic;
import static com.google.hashcode.entity.Slide.getSlidesWithVerticalPhotos;
import static com.google.hashcode.entity.SlideShow.getSlidesOrdered;
import static com.google.hashcode.utils.FilesPaths.*;


public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
//        bussinesLogic(EXAMPLE_INPUT_FILE_PATH, OUTPUT_DATA_SET_EXAMPLE_TXT);
//        bussinesLogic(LOVE_LANDSCAPES_INPUT_FILE_PATH, LOVE_LANDSCAPES_TXT);
        bussinesLogic(MEMORABLE_MOMENTS_INPUT_FILE_PATH, MEMORABLE_MOMENTS_TXT);
//        bussinesLogic(PET_PICTURES_INPUT_FILE_PATH, PET_PICTURES_DATA_SET_MEDIUM_TXT);
//        bussinesLogic(SHINY_SELFIE_PICTURES_INPUT_FILE_PATH, SHINY_SELFIE_DATA_SET_MEDIUM_TXT);
    }

    /**
     * Performs a pizza slicing
     *
     * @param inputFile  given input pizza file
     * @param outputFile a file slicing results
     * @throws IOException cant parse a pizza file
     */
    public static void bussinesLogic(String inputFile, String outputFile) throws IOException {
        Profiler profiler = new Profiler();

        PhotoContainer photoContainer = new PhotoContainer(parsePhotoLogic(inputFile));

        List<Photo> verticalPhotos = photoContainer.getPhotos()
                .stream()
                .filter(photo -> photo.getPosition().toString().equals(Position.VERTICAL.toString()))
                .collect(Collectors.toList());

        List<Photo> horizontalPhotos = photoContainer.getPhotos()
                .stream()
                .filter(photo -> photo.getPosition().toString().equals(Position.HORIZONTAL.toString()))
                .collect(Collectors.toList());

        List<Slide> slideWithVerticalPhotos = getSlidesWithVerticalPhotos(verticalPhotos);

        List<Slide> slideWithHorizontalPhotos = horizontalPhotos.stream()
                .map(p -> new Slide(new ArrayList<>(Arrays.asList(p)), p.getTags()))
                .collect(Collectors.toList());

        List<Slide> allSlides = new ArrayList<>();
        allSlides.addAll(slideWithVerticalPhotos);
        allSlides.addAll(slideWithHorizontalPhotos);

        allSlides = getSlidesOrdered(allSlides);

        IoUtils.writeToFile(outputFile, IoUtils.parseContainSlides(allSlides));

        LOGGER.info("FINISHED for " + inputFile + "!!!!!");
        LOGGER.info(profiler.measure(inputFile + " execution time: "));
    }

}
