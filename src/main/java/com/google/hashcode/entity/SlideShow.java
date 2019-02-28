package com.google.hashcode.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SlideShow {

    List<Slide> slides = new ArrayList<>();

    Integer score = 0;

    public static List<Slide> getSlidesOrdered(List<Slide> slides) {

        slides.sort(Comparator.comparing(slide -> slide.getTags().size()));

        List<Slide> slidesSorted = new ArrayList<>();

        for (int i = 0; i < slides.size(); i++) {

            slidesSorted.add(slides.get(i));

            // Hope it works !!!
            if (slides.size() % 2 != 0 &&
                    i == ((slides.size() + 1) / 2)) {
                slidesSorted.add(slides.get(i));
                break;
            }

            slidesSorted.add(slides.get(slides.size() - (i + 1)));

        }

        return slides;
    }
}
