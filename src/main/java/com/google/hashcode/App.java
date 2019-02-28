package com.google.hashcode;

import com.google.hashcode.entity.ObjectLogic;
import com.google.hashcode.entity.ContainCells;
import com.google.hashcode.entity.Step;
import com.google.hashcode.utils.IoUtils;
import com.google.hashcode.utils.Profiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.hashcode.utils.FilesPaths.*;
import static com.google.hashcode.utils.ContainerCellsMethods.*;


public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        bussinesLogic(EXAMPLE_INPUT_FILE_PATH, OUTPUT_DATA_SET_EXAMPLE_TXT);
        bussinesLogic(SMALL_INPUT_FILE_PATH, OUTPUT_DATA_SET_SMALL_TXT);
        bussinesLogic(MEDIUM_INPUT_FILE_PATH, OUTPUT_DATA_SET_MEDIUM_TXT);
        //takes to much time ~ 20 hours using intel -I5
        //bussinesLogic(BIG_INPUT_FILE_PATH, OUTPUT_DATA_SET_BIG_TXT);
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
        List<ContainCells> startPositions;
        List<ContainCells> output = new ArrayList<>();
        ObjectLogic pizza = new ObjectLogic(new File(inputFile), IoUtils.parseObjectLogic(inputFile), IoUtils.parseInstructions(inputFile));
        //get start positions
        startPositions = cutAllStartPositions(pizza);
        //get All steps
        Map<ContainCells, List<Step>> availableSteps = getAvailableSteps(pizza, startPositions, output);
        while (!availableSteps.values().stream().allMatch(List::isEmpty)) {
            Step step = selectStep(availableSteps);
            performStep(pizza, step, startPositions, output);
            availableSteps = getAvailableSteps(pizza, startPositions, output);
            LOGGER.debug("OUTPUT AFTER A STEP: "
                    + "\n " + output);
            LOGGER.debug("start positions cells number: " + startPositions.stream()
                    .map(slice -> slice.cells.size())
                    .reduce(0, (integer, integer2) -> integer + integer2)
            );
        }
        IoUtils.writeToFile(outputFile, IoUtils.parseContainCells(output));
        LOGGER.info("FINISHED for " + inputFile + "!!!!!");
        LOGGER.info("sliced cells number: " + output.stream()
                .map(slice -> slice.cells.size())
                .reduce(0, (integer, integer2) -> integer + integer2));
        LOGGER.info(profiler.measure(inputFile + " execution time: "));
    }

}
